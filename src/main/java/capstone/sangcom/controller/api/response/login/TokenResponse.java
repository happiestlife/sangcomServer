package capstone.sangcom.controller.api.response.login;

import lombok.Data;

@Data
public class TokenResponse {
    private final String access_token;
    private final String refresh_token;
}
