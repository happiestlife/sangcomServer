package capstone.sangcom.repository.reply;

import capstone.sangcom.entity.dto.replySection.ReplyCreateDTO;
import capstone.sangcom.entity.dto.replySection.ReplyDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class MySqlReplyRepository implements ReplyRepository {

    private final String REPLY_TABLE = "reply";

    private final String REPLY_GOOD_TABLE = "replygood";
    
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper rowMapper;

    public MySqlReplyRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = new ReplyRowMapper();
    }

    @Override
    public int create(ReplyCreateDTO replyCreateDTO) {
        String query = "INSERT INTO " + REPLY_TABLE + " (body, user_id, board_id, parent_id, level) VALUES (:body, :user_id, :board_id, :parent_id, :level)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("body", replyCreateDTO.getBody())
                .addValue("user_id", replyCreateDTO.getUserId())
                .addValue("board_id", replyCreateDTO.getBoardId())
                .addValue("parent_id", replyCreateDTO.getParentId())
                .addValue("level", replyCreateDTO.getLevel());

        KeyHolder key = new GeneratedKeyHolder();
        int update = jdbcTemplate.update(query, params, key, new String[]{"reply_id"});

        if(update == 1)
            return key.getKey().intValue();
        else
            return -1;
    }

    @Override
    public int getReplyCount(int boardId) {
        String query = "SELECT COUNT(reply_id) AS replyCount FROM " + REPLY_TABLE + " WHERE board_id = :board_id";
        return jdbcTemplate.queryForObject(query,
                new MapSqlParameterSource()
                        .addValue("board_id", boardId),
                Integer.class);
    }

    @Override
    public List<String> getReplyCreateByUserId(int replyId, String userId) {
        String query = "SELECT reply_id FROM " + REPLY_TABLE + " WHERE reply_id = :reply_id AND user_id = :user_id";
        return jdbcTemplate.query(query,
                new MapSqlParameterSource()
                        .addValue("reply_id", replyId)
                        .addValue("user_id", userId),
                ((rs, rowNum) -> rs.getString("reply_id"))
        );
    }

    @Override
    public List<String> getUserIdsAtBoard(int boardId) {
        String query = "SELECT user_id FROM " + REPLY_TABLE + " WHERE board_id = :board_id";
        return jdbcTemplate.query(query,
                new MapSqlParameterSource()
                        .addValue("board_id", boardId),
                (rs, rowNum) -> rs.getString("user_id"));
    }

    @Override
    public List<String> getUserIdsAtReply(int parentReplyId) {
        String query = "SELECT user_id FROM " + REPLY_TABLE + " WHERE reply_id = :reply_id";
        return jdbcTemplate.query(query,
                new MapSqlParameterSource()
                        .addValue("reply_id", parentReplyId),
                (rs, rowNum) -> rs.getString("user_id"));
    }

    @Override
    public ReplyDTO getOneReply(String userId, int boardId, int replyId) {
        String query = "SELECT " + REPLY_TABLE + ".*, " +
                "(SELECT COUNT(reply_id) FROM " + REPLY_GOOD_TABLE + " WHERE " + REPLY_GOOD_TABLE + ".reply_id = " + REPLY_TABLE + ".reply_id) AS goodCount, " +
                "IF((SELECT COUNT(reply_id) FROM " + REPLY_GOOD_TABLE + " WHERE user_id = :user_id AND " + REPLY_GOOD_TABLE + ".reply_id = " + REPLY_TABLE + ".reply_id) > 0, 'Y', 'N') AS goodCheck " +
                "FROM " + REPLY_TABLE + " WHERE board_id = :board_id AND reply_id = :reply_id";

        List<ReplyDTO> rs = jdbcTemplate.query(query,
                                new MapSqlParameterSource()
                                        .addValue("user_id", userId)
                                        .addValue("board_id", boardId)
                                        .addValue("reply_id", replyId),
                                rowMapper);

        if(rs.size() == 1)
            return rs.get(0);
        else
            return null;
    }

    @Override
    public List<ReplyDTO> getAllReplies(String userId, int boardId) {
        String query = "SELECT " + REPLY_TABLE + ".*, " +
                "(SELECT COUNT(reply_id) FROM " + REPLY_GOOD_TABLE + " WHERE " + REPLY_GOOD_TABLE + ".reply_id = " + REPLY_TABLE + ".reply_id) AS goodCount, " +
                "IF((SELECT COUNT(reply_id) FROM " + REPLY_GOOD_TABLE + " WHERE user_id = :user_id AND " + REPLY_GOOD_TABLE + ".reply_id = " + REPLY_TABLE + ".reply_id) > 0, 'Y', 'N') AS goodCheck " +
                "FROM " + REPLY_TABLE + " WHERE board_id = :board_id ORDER BY parent_id, level, regdate";

        return jdbcTemplate.query(query,
                new MapSqlParameterSource()
                        .addValue("user_id", userId)
                        .addValue("board_id", boardId),
                rowMapper);
    }

    @Override
    public boolean update(int parentId, int replyId) {
        String query = "UPDATE " + REPLY_TABLE + " SET parent_id = :parent_id WHERE reply_id = :reply_id";
        int rs = jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("parent_id", parentId)
                        .addValue("reply_id", replyId));

        if(rs == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean update(String body, int replyId) {
        String query = "UPDATE " + REPLY_TABLE + " SET body = :body WHERE reply_id = :reply_id";
        int rs = jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("body", body)
                        .addValue("reply_id", replyId));

        if(rs == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean delete(int replyId, int parentId) {
        String query = "DELETE FROM " + REPLY_TABLE + " WHERE reply_id = :reply_id OR parent_id = :parent_id";
        int rs = jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("reply_id", replyId)
                        .addValue("parent_id", parentId));

        if(rs == 1)
            return true;
        else
            return false;
    }

    private class ReplyRowMapper implements RowMapper<ReplyDTO> {
        @Override
        public ReplyDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ReplyDTO(
                    rs.getInt("reply_id"),
                    rs.getString("body"),
                    rs.getString("user_id"),
                    rs.getString("regdate"),
                    rs.getInt("board_id"),
                    rs.getInt("parent_id"),
                    rs.getInt("level"),
                    rs.getInt("goodCount"),
                    rs.getString("goodCheck"),
                    "N"
            );
        }
    }

}
