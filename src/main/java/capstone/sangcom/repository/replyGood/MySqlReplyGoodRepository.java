package capstone.sangcom.repository.replyGood;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MySqlReplyGoodRepository implements ReplyGoodRepository{

    private final String REPLY_GOOD_TABLE = "replygood";

    private final NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public boolean insert(int replyId, String userId) {
        String query = "INSERT INTO " + REPLY_GOOD_TABLE + "(user_id, reply_id) VALUES(:user_id, :reply_id)";
        int rs = jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("user_id", userId)
                        .addValue("reply_id", replyId)
        );

        if(rs == 1)
            return true;
        else
            return false;
    }

    @Override
    public int getReplyGoodId(int replyId, String userId) {
        String query = "SELECT good_id FROM " + REPLY_GOOD_TABLE + " WHERE reply_id = :reply_id AND user_id = :user_id";
        List<Integer> result = jdbcTemplate.query(query,
                new MapSqlParameterSource()
                        .addValue("reply_id", replyId)
                        .addValue("user_id", userId),
                (rs, rowNum) -> rs.getInt("good_id")
        );

        if (!result.isEmpty())
            return result.get(0);
        else
            return -1;
    }

    @Override
    public int getGoodCount(int replyId) {
        String query = "SELECT COUNT(user_id) FROM " + REPLY_GOOD_TABLE + " WHERE reply_id = :reply_id";
        List<Integer> result = jdbcTemplate.query(query,
                new MapSqlParameterSource()
                        .addValue("reply_id", replyId),
                (rs, rowNum) -> rs.getInt(1)
        );

        if(!result.isEmpty())
            return result.get(0);
        else
            return -1;
    }

    @Override
    public boolean delete(int goodId) {
        String query = "DELETE FROM " + REPLY_GOOD_TABLE + " WHERE good_id = :good_id";
        int rs = jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("good_id", goodId)
        );

        if(rs == 1)
            return true;
        else
            return false;
    }
}
