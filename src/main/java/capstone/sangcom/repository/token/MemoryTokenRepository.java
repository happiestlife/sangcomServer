package capstone.sangcom.repository.token;

import capstone.sangcom.repository.dao.auth.TokenDAO;

import java.util.HashMap;

public class MemoryTokenRepository implements TokenRepository{

    private final HashMap<String, String> repository;

    public MemoryTokenRepository() {
        this.repository = new HashMap<>();
    }

    @Override
    public String insert(TokenDAO tokenDao) {
        if (!repository.containsKey(tokenDao.getId())) {
            repository.put(tokenDao.getRefreshToken(), tokenDao.getId());

            return tokenDao.getRefreshToken();
        } else
            return null;
    }

    @Override
    public TokenDAO findByToken(String token) {
        if(repository.containsKey(token)){
            String id = repository.get(token);

            return new TokenDAO(id, token);
        } else
            return null;
    }

    @Override
    public String update(TokenDAO tokenDao) {
        if(repository.containsKey(tokenDao.getRefreshToken())){
            repository.remove(tokenDao.getRefreshToken());
            repository.put(tokenDao.getRefreshToken(), tokenDao.getId());

            return tokenDao.getRefreshToken();
        }else
            return null;
    }

    @Override
    public String delete(String token) {
        if (repository.containsKey(token)) {
            repository.remove(token);

            return token;
        }else
            return null;
    }
}
