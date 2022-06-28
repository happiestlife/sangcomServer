package capstone.sangcom.service.token;

import capstone.sangcom.controller.api.response.login.TokenResponse;
import capstone.sangcom.entity.User;

public interface TokenService {
    public String createNewTokenWithRefreshToken(String refreshToken);
    public TokenResponse createToken(User user);
}
