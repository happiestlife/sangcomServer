package capstone.sangcom.repository.dao;

import lombok.Data;

@Data
public class TokenDAO {

    private final String id;
    private final String refreshToken;

}
