package capstone.sangcom.repository.token;

import capstone.sangcom.repository.dao.TokenDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MySqlTokenRepository implements TokenRepository {

    private final String TOKEN_TABLE = "token";

    private final class TokenRowMapper implements RowMapper<TokenDao> {
        @Override
        public TokenDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new TokenDao(
                    rs.getString("id"),
                    rs.getString("token")
            );
        }
    }

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final TokenRowMapper rowMapper;

    public MySqlTokenRepository(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        rowMapper = new TokenRowMapper();
    }

    @Override
    public String insert(TokenDao tokenDao) {
        String query = "INSERT INTO " + TOKEN_TABLE + " VALUES( :id, :token )";
        Map<String, Object> params = new HashMap<>();
        params.put("id", tokenDao.getId());
        params.put("token", tokenDao.getRefreshToken());

        int result = jdbcTemplate.update(query, params);

        if(result == 0)
            return null;

        return tokenDao.getRefreshToken();
    }

    @Override
    public TokenDao findByToken(String token) {
        String query = "SELECT * FROM " + TOKEN_TABLE + " WHERE token = :token";
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);

        List<TokenDao> result = jdbcTemplate.query(query, params, rowMapper);

        if(result.size() == 1)
            return result.get(0);
        else
            return null;
    }

    @Override
    public String update(TokenDao tokenDao) {
        String query = "UPDATE " + TOKEN_TABLE + " SET token = :token WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", tokenDao.getId());
        params.put("token", tokenDao.getRefreshToken());

        int result = jdbcTemplate.update(query, params);

        if(result == 0)
            return null;

        return tokenDao.getRefreshToken();
    }

    @Override
    public String delete(String token) {
        String query = "DELETE FROM " + TOKEN_TABLE + " WHERE token = :token";
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);

        int result = jdbcTemplate.update(query, params);

        if(result == 0)
            return null;

        return token;
    }

    public List<TokenDao> findAll(){
        String query = "SELECT * FROM " + TOKEN_TABLE;

        return jdbcTemplate.query(query, rowMapper);
    }

    public void removeAll() {
        String query = "DELETE FROM " + TOKEN_TABLE;

        jdbcTemplate.update(query, (SqlParameterSource) null);
    }
}
