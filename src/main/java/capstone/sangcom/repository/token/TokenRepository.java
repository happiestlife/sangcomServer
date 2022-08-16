package capstone.sangcom.repository.token;

import capstone.sangcom.entity.dao.auth.TokenDAO;

public interface TokenRepository {

    public String insert(TokenDAO tokenDao);
    public TokenDAO findByToken(String token);
    public String update(TokenDAO tokenDao);
    public String deleteByToken(String token);
    public boolean deleteById(String id);
}
