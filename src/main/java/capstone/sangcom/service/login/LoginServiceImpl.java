package capstone.sangcom.service.login;

import capstone.sangcom.controller.api.response.login.LoginResponse;
import capstone.sangcom.entity.dto.loginSection.find.FindPasswordDTO;
import capstone.sangcom.entity.dto.loginSection.login.LoginDTO;
import capstone.sangcom.entity.User;
import capstone.sangcom.repository.user.UserRepository;
import capstone.sangcom.service.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Override
    public LoginResponse login(LoginDTO loginDTO) {
        User user = userRepository.findById(loginDTO.getId());
        if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return new LoginResponse("success", tokenService.createToken(user), user.getRole());
        } else
            return null;
    }

    @Override
    public String findPassword(FindPasswordDTO findPasswordDTO) {
        return null;
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.insert(user);
    }

}
