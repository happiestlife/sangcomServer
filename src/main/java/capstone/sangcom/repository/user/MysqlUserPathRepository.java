package capstone.sangcom.repository.user;

import capstone.sangcom.entity.dao.profile.ImagePathDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MysqlUserPathRepository implements UserPathRepository{

    private final String IMAGE_PATH_TABLE = "imagepath";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper rowMapper;

    public MysqlUserPathRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = new UserPathRowMapper();
    }


    @Override
    public ImagePathDAO imageUpload(ImagePathDAO imagePathDAO) {
        return null;
    }

    @Override
    public boolean findById(String id, String path) {
        String query = "SELECT path FROM " + IMAGE_PATH_TABLE + " WHERE id = :id";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("path", path);
        params.put("id", id);

//        List<User> rs = jdbcTemplate.query(query, params, rowMapper);
        List<String> result = jdbcTemplate.query(query, params,
                (rs, rowNum) -> rs.getString("path")
        );

        if(result.isEmpty())
            return true;
        else
            return false;
    }

    @Override
    public List<String> find(String userId) {
        String query = "SELECT path FROM " + IMAGE_PATH_TABLE + " WHERE user_id = :userId";

        return jdbcTemplate.query(query,
                new MapSqlParameterSource()
                        .addValue("user_id", userId),
                (rs, rowNum) -> rs.getString("path"));
    }

    @Override
    public String showImage(String userId) {
        String query = "SELECT path FROM " + IMAGE_PATH_TABLE + " WHERE user_id = :user_id";

        return jdbcTemplate.query(query, new MapSqlParameterSource().addValue("user_id", userId), new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                return rs.getString("path");
            }
        });
    }

    @Override
    public boolean deleteImage(String userId) {
        String query = "DELETE FROM " + IMAGE_PATH_TABLE + " WHERE user_id = :user_id";

        if(jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("user_id", userId)) != 1)
            return false;
        else
            return true;
    }

    private class UserPathRowMapper implements RowMapper<ImagePathDAO> {
        @Override
        public ImagePathDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ImagePathDAO(
                    rs.getString("image_id"),
                    rs.getString("path")
            );
        }
    }
}
