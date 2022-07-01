package capstone.sangcom.service.token;

import capstone.sangcom.config.auth.JwtManager;
import capstone.sangcom.controller.api.response.login.TokenResponse;
import capstone.sangcom.entity.User;
import capstone.sangcom.repository.dao.TokenDAO;
import capstone.sangcom.repository.token.TokenRepository;
import capstone.sangcom.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Override
    public String createNewTokenWithRefreshToken(String refreshToken) {
        TokenDAO token = tokenRepository.findByToken(refreshToken);
        if(token == null || (JwtManager.isValidToken(token.getRefreshToken()) == false)){
            tokenRepository.delete(refreshToken);

            return null;
        }

        User user = userRepository.findById(token.getId());
        return JwtManager.createAccessToken(user);
    }

    @Override
    public TokenResponse createToken(User user) {
        String accessToken = JwtManager.createAccessToken(user);
        String refreshToken = JwtManager.createRefreshToken(user);

        String result = tokenRepository.insert(new TokenDAO(user.getId(), refreshToken));

        if(result != null)
            return new TokenResponse(accessToken, refreshToken);
        else
            return null;
    }
}
