package capstone.sangcom.repository.dao;

import lombok.Data;

@Data
public class TokenDao {

    private final String id;
    private final String refreshToken;

}
