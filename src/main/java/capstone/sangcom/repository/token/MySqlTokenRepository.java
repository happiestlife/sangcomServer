package capstone.sangcom.repository.token;

import capstone.sangcom.repository.dao.TokenDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
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

@Slf4j
@Repository
public class MySqlTokenRepository implements TokenRepository {

    private final String TOKEN_TABLE = "token";

    private final class TokenRowMapper implements RowMapper<TokenDAO> {
        @Override
        public TokenDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new TokenDAO(
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
    public String insert(TokenDAO tokenDao) {
        String query = "INSERT INTO " + TOKEN_TABLE + " VALUES( :id, :token )";
        Map<String, Object> params = makeParam(tokenDao);

        try {
            int rs = jdbcTemplate.update(query, params);

            if (rs == 1)
                return tokenDao.getRefreshToken();
            else
                return null;
        }catch (DuplicateKeyException e){
            log.info("[DuplicateKeyException] Id is already exist");

            return null;
        }
    }

    @Override
    public TokenDAO findByToken(String token) {
        String query = "SELECT * FROM " + TOKEN_TABLE + " WHERE token = :token";
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);

        List<TokenDAO> result = jdbcTemplate.query(query, params, rowMapper);

        if(result.size() == 1)
            return result.get(0);
        else
            return null;
    }

    @Override
    public String update(TokenDAO tokenDao) {
        String query = "UPDATE " + TOKEN_TABLE + " SET token = :token WHERE id = :id";
        Map<String, Object> params = makeParam(tokenDao);

        int rs = jdbcTemplate.update(query, params);

        if(rs == 1)
            return tokenDao.getRefreshToken();
        else
            return null;
    }

    @Override
    public String delete(String token) {
        String query = "DELETE FROM " + TOKEN_TABLE + " WHERE token = :token";
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);

        int rs = jdbcTemplate.update(query, params);

        if(rs == 1)
            return token;
        else
            return null;
    }

    public List<TokenDAO> findAll(){
        String query = "SELECT * FROM " + TOKEN_TABLE;

        return jdbcTemplate.query(query, rowMapper);
    }

    public void removeAll() {
        String query = "DELETE FROM " + TOKEN_TABLE;

        jdbcTemplate.update(query, (SqlParameterSource) null);
    }

    private Map<String, Object> makeParam(TokenDAO tokenDAO) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", tokenDAO.getId());
        params.put("token", tokenDAO.getRefreshToken());

        return params;
    }
}
