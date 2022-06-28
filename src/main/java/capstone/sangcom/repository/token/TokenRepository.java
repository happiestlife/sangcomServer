package capstone.sangcom.repository.token;

import capstone.sangcom.repository.dao.TokenDao;

public interface TokenRepository {

    public String insert(TokenDao tokenDao);
    public TokenDao findByToken(String token);
    public String update(TokenDao tokenDao);
    public String delete(String token);

}
