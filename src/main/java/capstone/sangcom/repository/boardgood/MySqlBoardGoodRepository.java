package capstone.sangcom.repository.boardgood;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MySqlBoardGoodRepository implements BoardGoodRepository {

    private final String BOARD_GOOD_TABLE = "boardgood";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public boolean insert(int boardId, String userId) {
        String query = "INSERT INTO " + BOARD_GOOD_TABLE + "(user_id, board_id) VALUES(:user_id, :board_id)";
        int rs = jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("board_id", boardId)
                        .addValue("user_id", userId));

        if(rs == 1)
            return true;
        else
            return false;
    }

    @Override
    public int getBoardGoodCheckedByUser(int boardId, String userId) {
        String query = "SELECT good_id FROM " + BOARD_GOOD_TABLE + " WHERE board_id = :board_id AND user_id = :user_id";
        List<Integer> goodId = jdbcTemplate.query(
                query,
                new MapSqlParameterSource()
                        .addValue("board_id", boardId)
                        .addValue("user_id", userId)
                , (rs, rowNum) -> rs.getInt("board_id")
        );

        if(!goodId.isEmpty())
            return goodId.get(0);
        else
            return -1;
    }

    @Override
    public int countBoardGood(int boardId) {
        String query = "SELECT COUNT(board_id) AS goodCount FROM " + BOARD_GOOD_TABLE + " WHERE board_id = :board_id";
        return jdbcTemplate.query(
                query,
                new MapSqlParameterSource()
                        .addValue("board_id", boardId)
                , new ResultSetExtractor<Integer>() {
                    @Override
                    public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                        rs.next();
                        return rs.getInt("goodCount");
                    }
                }
        );
    }

    @Override
    public boolean delete(int boardId) {
        String query = "DELETE FROM " + BOARD_GOOD_TABLE + " WHERE board_id = :board_id";
        int rs = jdbcTemplate.update(query
                , new MapSqlParameterSource()
                        .addValue("board_id", boardId));

        if(rs == 1)
            return true;
        else
            return false;
    }
}
