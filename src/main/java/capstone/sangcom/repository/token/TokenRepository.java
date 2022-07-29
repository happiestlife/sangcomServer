package capstone.sangcom.repository.token;

import capstone.sangcom.repository.dao.auth.TokenDAO;

public interface TokenRepository {

    public String insert(TokenDAO tokenDao);
    public TokenDAO findByToken(String token);
    public String update(TokenDAO tokenDao);
    public String delete(String token);

}
