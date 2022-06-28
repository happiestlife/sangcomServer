package capstone.sangcom.controller.api.response.login;

import lombok.Data;

@Data
public class LoginResponse {
    private final String success;
    private final TokenResponse token;
}
