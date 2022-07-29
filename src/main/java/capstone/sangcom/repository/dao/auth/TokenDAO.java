package capstone.sangcom.repository.dao.auth;

import lombok.Data;

@Data
public class TokenDAO {

    private final String id;
    private final String refreshToken;

}
