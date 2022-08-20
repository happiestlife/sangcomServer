package capstone.sangcom.repository.scrap;

import capstone.sangcom.entity.dto.boardSection.BoardDTO;
import capstone.sangcom.repository.board.MySqlBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MySqlScrapRepository implements ScrapRepository {

    private final String SCRAP_TABLE = "scrap";

    private final String BOARD_TABLE = "board";

    private final String BOARD_GOOD_TABLE = "boardgood";

    private final String REPLY_TABLE = "reply";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<BoardDTO> rowMapper = new BoardRowMapper();

    @Override
    public boolean insert(int boardId, String userId) {
        String query = "INSERT INTO " + SCRAP_TABLE + "(board_id, user_id) VALUES(:board_id, :user_id)";
        return decideUpdateSuccess(jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("board_id", boardId)
                        .addValue("user_id", userId)));
    }

    @Override
    public int getScrapId(int boardId, String userId) {
        String query = "SELECT scrap_id FROM " + SCRAP_TABLE + " WHERE board_id = :board_id AND user_id = :user_id";
        List<Integer> result = jdbcTemplate.query(query,
                new MapSqlParameterSource()
                        .addValue("board_id", boardId)
                        .addValue("user_id", userId),
                (rs, rowNum) -> rs.getInt("scrap_id"));
        if(!result.isEmpty())
            return result.get(0);
        else
            return -1;
    }

    @Override
    public List<BoardDTO> getScrapBoardData(String userId) {
        String query =
                "SELECT " + BOARD_TABLE + ".*, " +
                "(SELECT COUNT(board_id) FROM " + BOARD_GOOD_TABLE + " WHERE " + BOARD_TABLE + ".board_id = " + BOARD_GOOD_TABLE + ".board_id) AS goodCount, " +
                "(SELECT COUNT(board_id) FROM " + REPLY_TABLE + " WHERE " + BOARD_TABLE + ".board_id = " + REPLY_TABLE + ".board_id) AS replyCount, " +
                "(SELECT COUNT(board_id) FROM " + SCRAP_TABLE + " WHERE " + BOARD_TABLE + ".board_id = " + SCRAP_TABLE + ".board_id) AS scrapCount " +
                "FROM " + SCRAP_TABLE + " INNER JOIN " + BOARD_TABLE + " ON " + BOARD_TABLE + ".board_id = " + SCRAP_TABLE + ".board_id " +
                "WHERE " + SCRAP_TABLE + ".user_id = :user_id ORDER BY regdate DESC";

        return jdbcTemplate.query(query,
                new MapSqlParameterSource()
                        .addValue("user_id", userId),
                rowMapper);
    }

    @Override
    public int getScrapCount(int boardId) {
        String query = "SELECT COUNT(scrap_id) FROM " + SCRAP_TABLE + " WHERE board_id = :board_id";
        List<Integer> result = jdbcTemplate.query(query,
                new MapSqlParameterSource()
                        .addValue("board_id", boardId),
                (rs, rowNum) -> rs.getInt(1));

        if(!result.isEmpty())
            return result.get(0);
        else
            return -1;
    }

    @Override
    public boolean delete(int scrapId) {
        String query = "DELETE FROM " + SCRAP_TABLE + " WHERE scrap_id = :scrap_id";
        return decideUpdateSuccess(jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("scrap_id", scrapId)));
    }

    private boolean decideUpdateSuccess(int effectedCount) {
        if(effectedCount == 1)
            return true;
        else
            return false;
    }

    private class BoardRowMapper implements RowMapper<BoardDTO> {

        @Override
        public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BoardDTO(
                    rs.getInt("board_id"),
                    rs.getString("title"),
                    rs.getString("body"),
                    rs.getString("user_id"),
                    rs.getString("regdate"),
                    rs.getString("type"),
                    rs.getInt("goodCount"),
                    rs.getInt("replyCount"),
                    rs.getInt("scrapCount"));
        }
    }
}
