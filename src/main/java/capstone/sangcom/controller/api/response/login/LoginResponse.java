package capstone.sangcom.controller.api.response.login;

import capstone.sangcom.entity.UserRole;
import lombok.Data;

@Data
public class LoginResponse {

    private final String success;
    private final TokenResponse token;
    private final UserRole role;

}
