package capstone.sangcom.repository.board;

import capstone.sangcom.dto.boardSection.BoardDTO;
import capstone.sangcom.dto.boardSection.BoardDetailDTO;
import capstone.sangcom.dto.boardSection.ReadBoardDTO;
import capstone.sangcom.entity.UserRole;
import capstone.sangcom.repository.dao.board.BoardDAO;
import capstone.sangcom.repository.dao.board.ImagePathDAO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MySqlBoardRepository implements BoardRepository {

    private final String BOARD_TABLE = "board";

    private final String BOARD_PATH_TABLE = "boardpath";

    private final String BOARD_GOOD_TABLE = "boardgood";

    private final String REPLY_TABLE = "reply";

    private final String SCRAP_TABLE = "scrap";

    private class BoardRowMapper implements RowMapper<BoardDTO>{

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

    private class BoardDetailRowMapper implements RowMapper<BoardDetailDTO>{

        @Override
        public BoardDetailDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BoardDetailDTO(
                    rs.getInt("board_id"),
                    rs.getString("title"),
                    rs.getString("body"),
                    rs.getString("user_id"),
                    rs.getString("regdate"),
                    rs.getString("type"),
                    rs.getInt("goodCount"),
                    rs.getInt("replyCount"),
                    rs.getInt("scrapCount"),
                    rs.getString("goodCheck"),
                    rs.getString("scrapCheck"),
                    "N"
            );
        }
    }

    private final RowMapper<BoardDTO> boardRowMapper;

    private final RowMapper<BoardDetailDTO> boardDetailRowMapper;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MySqlBoardRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        jdbcTemplate = namedParameterJdbcTemplate;
        boardRowMapper = new BoardRowMapper();
        boardDetailRowMapper = new BoardDetailRowMapper();
    }

    @Override
    public BoardDAO insert(BoardDAO boardDAO, List<String> paths) {
        KeyHolder key = new GeneratedKeyHolder();

        String query = "INSERT INTO " + BOARD_TABLE + " (title, body, user_id, type) VALUES (:title, :body, :user_id, :type))";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", boardDAO.getTitle())
                .addValue("body", boardDAO.getBody())
                .addValue("user_id", boardDAO.getUser_id())
                .addValue("type", boardDAO.getType());

        if (jdbcTemplate.update(query, params, key, new String[]{"board_id"}) != 1)
            return null;

        int board_id = key.getKey().intValue();

        for (String path : paths) {
            query = "INSERT INTO " + BOARD_PATH_TABLE + " (board_id, path) VALUES (:board_id, :path)";
            params = new MapSqlParameterSource()
                    .addValue("board_id", board_id)
                    .addValue("path", path);

            if (jdbcTemplate.update(query, params) != 1)
                return null;
        }
        return boardDAO;
    }


    @Override
    public BoardDTO findBoard(String type, String title) {
        String query = "SELECT " + BOARD_TABLE + ".*, " +
                "(SELECT COUNT(board_id) FROM " + BOARD_GOOD_TABLE + " WHERE " + BOARD_TABLE + ".board_id=" + BOARD_GOOD_TABLE + ".board_id) AS goodCount, " +
                "(SELECT COUNT(board_id) FROM " + REPLY_TABLE + " WHERE " + BOARD_TABLE + ".board_id=" + REPLY_TABLE + ".board_id) AS replyCount, " +
                "(SELECT COUNT(board_id) FROM " + SCRAP_TABLE + " WHERE " + BOARD_TABLE + ".board_id=" + SCRAP_TABLE + ".board_id) AS scrapCount " +
                "FROM " + BOARD_TABLE + " WHERE type = :type AND (title LIKE :title OR body LIKE :title) ORDER BY regdate DESC";

        Map<String, Object> params = new HashMap<>();
        params.put("type", type);
        params.put("title", title);

        List<BoardDTO> rs = jdbcTemplate.query(query, params, boardRowMapper);
        if(rs.size() != 1)
            return null;
        else
            return rs.get(0);
    }

    @Override
    public List<BoardDTO> readAll(String type) {
        String query = "SELECT " + BOARD_TABLE + ".*, " +
                "(SELECT COUNT(board_id) FROM " + BOARD_GOOD_TABLE + " WHERE " + BOARD_TABLE + ".board_id=" + BOARD_GOOD_TABLE + ".board_id) AS goodCount, " +
                "(SELECT COUNT(board_id) FROM " + REPLY_TABLE + " WHERE " + BOARD_TABLE + ".board_id=" + REPLY_TABLE + ".board_id) AS replyCount, " +
                "(SELECT COUNT(board_id) FROM " + SCRAP_TABLE + " WHERE " + BOARD_TABLE + ".board_id=" + SCRAP_TABLE + ".board_id) AS scrapCount " +
                "FROM " + BOARD_TABLE + " WHERE type = :type ORDER BY regdate DESC";

        return jdbcTemplate.query(query,
                new MapSqlParameterSource().
                        addValue("type", type),
                boardRowMapper);
    }

    @Override
    public ReadBoardDTO readBoard(String userId, String boardId) {
        String query = "SELECT " + BOARD_TABLE + ".*, " +
                "(SELECT COUNT(board_id) FROM " + BOARD_GOOD_TABLE + " WHERE " + BOARD_TABLE + ".board_id=" + BOARD_GOOD_TABLE + ".board_id) AS goodCount, " +
                "(SELECT COUNT(board_id) FROM " + REPLY_TABLE + " WHERE " + BOARD_TABLE + ".board_id=" + REPLY_TABLE + ".board_id) AS replyCount, " +
                "(SELECT COUNT(board_id) FROM " + SCRAP_TABLE + " WHERE " + BOARD_TABLE + ".board_id=" + SCRAP_TABLE + ".board_id) AS scrapCount " +
                "IF((SELECT COUNT(board_id) FROM " + BOARD_GOOD_TABLE + " WHERE user_id=:user_id AND " + BOARD_GOOD_TABLE + ".board_id=" + BOARD_TABLE + ".board_id) > 0, 'Y', 'N') AS goodCheck, " +
                "IF((SELECT COUNT(board_id) FROM " + SCRAP_TABLE + " WHERE user_id=:user_id AND " + SCRAP_TABLE + ".board_id=" + BOARD_TABLE + ".board_id) > 0, 'Y', 'N') AS scrapCheck " +
                "FROM " + BOARD_TABLE + " WHERE board_id = :board_id";

        HashMap<String, Object> params = new HashMap<>();
        params.put("user_id", userId);
        params.put("board_id", boardId);

        BoardDetailDTO detailBoard = jdbcTemplate.queryForObject(query, params, boardDetailRowMapper);
        if (userId.equals(detailBoard.getUser_id()))
            detailBoard.setUserCheck("Y");

        query = "SELECT path FROM " + BOARD_PATH_TABLE + " WHERE board_id=:board_id";
        List<String> paths = jdbcTemplate.query(query,
                new MapSqlParameterSource()
                        .addValue("board_id", boardId),
                (rs, rowNum) -> {
                    return rs.getString("path");
                }
        );

        return new ReadBoardDTO(detailBoard, paths);
    }

    @Override
    public boolean updateBoard() {
        return false;
    }

    @Override
    public boolean deleteBoard(UserRole userRole, String boardId, String id) {
        return false;
    }
}
