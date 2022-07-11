package capstone.sangcom.repository.board.board;

import capstone.sangcom.dto.boardSection.BoardDTO;
import capstone.sangcom.dto.boardSection.BoardDetailDTO;
import capstone.sangcom.dto.boardSection.ReadBoardDTO;
import capstone.sangcom.dto.boardSection.UpdateBoardDTO;
import capstone.sangcom.repository.dao.board.BoardDAO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MySqlBoardRepository implements BoardRepository {

    private final String BOARD_TABLE = "board";

    private final String BOARD_GOOD_TABLE = "boardgood";

    private final String REPLY_TABLE = "reply";

    private final String SCRAP_TABLE = "scrap";

    private final RowMapper<BoardDTO> boardRowMapper;

    private final RowMapper<BoardDetailDTO> boardDetailRowMapper;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MySqlBoardRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        jdbcTemplate = namedParameterJdbcTemplate;
        boardRowMapper = new BoardRowMapper();
        boardDetailRowMapper = new BoardDetailRowMapper();
    }

    @Override
    public int insert(BoardDAO boardDAO) {
        KeyHolder key = new GeneratedKeyHolder();

        // 게시글 저장
        String query = "INSERT INTO " + BOARD_TABLE + " (title, body, user_id, type) VALUES (:title, :body, :user_id, :type))";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", boardDAO.getTitle())
                .addValue("body", boardDAO.getBody())
                .addValue("user_id", boardDAO.getUser_id())
                .addValue("type", boardDAO.getType());

        if (jdbcTemplate.update(query, params, key, new String[]{"board_id"}) != 1)
            return -1;

        return key.getKey().intValue();
    }


    @Override
    public List<BoardDTO> findBoard(String type, String keyword) {
        // 검색어에 해당하는 게시글 목록 검색
        String query = "SELECT " + BOARD_TABLE + ".*, " +
                "(SELECT COUNT(board_id) FROM " + BOARD_GOOD_TABLE + " WHERE " + BOARD_TABLE + ".board_id=" + BOARD_GOOD_TABLE + ".board_id) AS goodCount, " +
                "(SELECT COUNT(board_id) FROM " + REPLY_TABLE + " WHERE " + BOARD_TABLE + ".board_id=" + REPLY_TABLE + ".board_id) AS replyCount, " +
                "(SELECT COUNT(board_id) FROM " + SCRAP_TABLE + " WHERE " + BOARD_TABLE + ".board_id=" + SCRAP_TABLE + ".board_id) AS scrapCount " +
                "FROM " + BOARD_TABLE + " WHERE type = :type AND (title LIKE :title OR body LIKE :title) ORDER BY regdate DESC";

        Map<String, Object> params = new HashMap<>();
        params.put("type", type);
        params.put("title", keyword);

        return jdbcTemplate.query(query, params, boardRowMapper);
    }

    @Override
    public List<BoardDTO> readAll(String type) {
        // 게시글 종류에 해당하는 모든 게시글 조회
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
    public BoardDetailDTO readBoard(String userId, int boardId) {
        // 하나의 게시글 정보 조회
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

        BoardDetailDTO boardDetail = jdbcTemplate.queryForObject(query, params, boardDetailRowMapper);
        if (boardDetail == null) return null;

        if (userId.equals(boardDetail.getUser_id()))
            boardDetail.setUserCheck("Y");

        // 해당 게시글의 이미지 경로 조회

        return boardDetail;
    }

    @Override
    public boolean updateBoard(UpdateBoardDTO updateBoardDTO) {
        String query = "UPDATE " + BOARD_TABLE + " SET title = :title, body = :body WHERE board_id = :board_id";
        Map<String, Object> params = new HashMap<>();
        params.put("title", updateBoardDTO.getTitle());
        params.put("body", updateBoardDTO.getBody());
        params.put("board_id", updateBoardDTO.getBoardId());

        if(jdbcTemplate.update(query, params) != 1)
            return false;
        else
            return true;
    }

    @Override
    public boolean deleteBoard(int boardId) {
        // 게시글을 작성한 사용자가 맞는지 확인 -> 서비스 계층에서 구현

        // 게시글 삭제
        String query = "DELETE FROM " + BOARD_TABLE + " WHERE board_id = :board_id";
        if(jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("board_id", boardId)) != 1)
            return false;
        else
            return true;

        // 게시글 이미지 경로 삭제

        // 이미지 삭제 -> 서비스 계층에서 구현
    }

    @Override
    public boolean isUserWriteBoard(int boardId, String userId) {
        String query = "SELECT user_id FROM " + BOARD_TABLE + " WHERE board_id = :board_id AND user_id = :user_id";
        HashMap<String, Object> params = new HashMap<>();
        params.put("board_id", boardId);
        params.put("user_id", userId);

        List<String> isUserWriteBoard = jdbcTemplate.query(query, params,
                (rs, rowNum) -> rs.getString("user_id")
        );

        if(isUserWriteBoard.isEmpty())
            return false;
        else
            return true;
    }

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
}
