package capstone.sangcom.repository.user;

import capstone.sangcom.entity.User;
import capstone.sangcom.entity.UserRole;
import capstone.sangcom.entity.dao.profile.ImagePathDAO;
import capstone.sangcom.entity.dto.replySection.ReplyDTO;
import capstone.sangcom.repository.reply.MySqlReplyRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<User> rs = jdbcTemplate.query(query, params, rowMapper);

        if(rs.isEmpty())
            return true;
        else
            return false;
    }

    @Override
    public List<String> find(String userId) {
        String query = "SELECT path FROM " + IMAGE_PATH_TABLE + " WHERE id = :id";

        return jdbcTemplate.query(query,
                new MapSqlParameterSource()
                        .addValue("id", userId),
                (rs, rowNum) -> rs.getString("path"));
    }

    @Override
    public String showImage(String userId) {
        String query = "SELECT path FROM " + IMAGE_PATH_TABLE + " WHERE id =:id";

        return jdbcTemplate.query(query, new MapSqlParameterSource().addValue("id", userId), new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                return rs.getString("path");
            }
        });
    }

    @Override
    public boolean deleteImage(String userId) {
        String query = "DELETE FROM " + IMAGE_PATH_TABLE + " WHERE id =:id";

        if(jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("id", userId)) != 1)
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
