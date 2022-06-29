package capstone.sangcom.repository.auth;

import capstone.sangcom.repository.dao.AuthStudentDAO;
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
public class MySqlAuthStudentRepository implements AuthStudentRepository {

    private final String AUTH_STUDENT_TABLE = "authstudent";

    private final class AuthStudentRowMapper implements RowMapper<AuthStudentDAO> {
        @Override
        public AuthStudentDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new AuthStudentDAO(
                    rs.getString("name"),
                    rs.getString("student_id")
            );
        }
    }

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final AuthStudentRowMapper rowMapper;

    public MySqlAuthStudentRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.rowMapper = new AuthStudentRowMapper();
    }

    @Override
    public AuthStudentDAO insert(AuthStudentDAO authStudentDAO) {
        String query = "INSERT INTO " + AUTH_STUDENT_TABLE + " VALUES( :student_id, :name )";

        Map<String, Object> params = makeParam(authStudentDAO);

        try{
            int rs = jdbcTemplate.update(query, params);

            if(rs == 1)
                return authStudentDAO;
            else
                return null;
        }catch (DuplicateKeyException e){
            log.info("[DuplicateKeyException] Name and student_id is already exist");

            return null;
        }
    }

    @Override
    public AuthStudentDAO find(AuthStudentDAO authStudentDAO) {
        String query = "SELECT * FROM " + AUTH_STUDENT_TABLE + " WHERE student_id = :student_id AND name = :name" ;
        Map<String, Object> params = makeParam(authStudentDAO);

        List<AuthStudentDAO> rs = jdbcTemplate.query(query, params, rowMapper);
        if(rs.size() == 1)
            return rs.get(0);
        else
            return null;
    }

    @Override
    public boolean delete(AuthStudentDAO authStudentDAO) {
        String query = "DELETE FROM " + AUTH_STUDENT_TABLE + " WHERE student_id = :student_id AND name = :name";
        Map<String, Object> params = makeParam(authStudentDAO);

        int rs = jdbcTemplate.update(query, params);

        if(rs == 1)
            return true;
        else
            return false;
    }

    public List<AuthStudentDAO> findAll() {
        String query = "SELECT * FROM " + AUTH_STUDENT_TABLE;

        return jdbcTemplate.query(query, rowMapper);
    }

    public void removeAll() {
        String query = "DELETE FROM " + AUTH_STUDENT_TABLE;

        jdbcTemplate.update(query, (SqlParameterSource) null);
    }

    private Map<String, Object> makeParam(AuthStudentDAO authStudentDAO) {
        Map<String, Object> params = new HashMap<>();
        params.put("student_id", authStudentDAO.getStudentId());
        params.put("name", authStudentDAO.getName());

        return params;
    }

}
