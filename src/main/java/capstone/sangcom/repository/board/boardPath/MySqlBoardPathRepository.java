package capstone.sangcom.repository.board.boardPath;

import capstone.sangcom.repository.dao.board.BoardPathDAO;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class MySqlBoardPathRepository implements BoardPathRepository{

    private final String BOARD_PATH_TABLE = "boardpath";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public BoardPathDAO insert(BoardPathDAO boardPathDAO) {
        String query = "INSERT INTO " + BOARD_PATH_TABLE + " (board_id, path) VALUES(:board_id, :path)";
        Map<String, Object> params = new HashMap<>();
        params.put("board_id", boardPathDAO.getBoard_id());
        params.put("path", boardPathDAO.getPath());

        if(jdbcTemplate.update(query, params) != 1)
            return null;
        else
            return boardPathDAO;
    }

    @Override
    public List<String> find(int boardId) {
        String query = "SELECT path FROM" + BOARD_PATH_TABLE + " WHERE board_id = :board_id";

        return jdbcTemplate.query(query,
                new MapSqlParameterSource()
                        .addValue("board_id", boardId),
                (rs, rowNum) -> rs.getString("path"));
    }

    @Override
    public void delete(int boardId) {
        String query = "DELETE FROM " + BOARD_PATH_TABLE + " WHERE board_id = :board_id";

        jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("board_id", boardId));
    }
}
