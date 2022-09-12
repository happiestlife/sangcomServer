package capstone.sangcom.service.token;

import capstone.sangcom.util.auth.JwtUtils;
import capstone.sangcom.controller.api.response.login.TokenResponse;
import capstone.sangcom.entity.User;
import capstone.sangcom.entity.dao.auth.TokenDAO;
import capstone.sangcom.repository.token.TokenRepository;
import capstone.sangcom.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    /**
     * Refresh 토큰이 유효한지 확인하고 맞다면 새로운 Access 토큰 발급
     */
    @Override
    public String createNewTokenWithRefreshToken(String refreshToken) {
        TokenDAO token = tokenRepository.findByToken(refreshToken);
        if(token == null || (JwtUtils.isValidToken(token.getRefreshToken()) == false)){
            tokenRepository.deleteByToken(refreshToken);

            return null;
        }

        User user = userRepository.findById(token.getId());
        return JwtUtils.createAccessToken(user);
    }

    /**
     * Refresh 토큰과 Access 토큰 발급
     */
    @Override
    public TokenResponse createToken(User user) {
        String accessToken = JwtUtils.createAccessToken(user);
        String refreshToken = JwtUtils.createRefreshToken(user);

        String result = tokenRepository.insert(new TokenDAO(user.getId(), refreshToken));
        if(result == null){
            tokenRepository.deleteById(user.getId());

            tokenRepository.insert(new TokenDAO(user.getId(), refreshToken));
        }

        return new TokenResponse(accessToken, refreshToken);
    }

    @Override
    public boolean delete(String id) {
        return tokenRepository.deleteById(id);
    }
}
