package capstone.sangcom.repository.board.board;

import capstone.sangcom.entity.dto.boardSection.BoardDTO;
import capstone.sangcom.entity.dto.boardSection.BoardDetailDTO;
import capstone.sangcom.entity.dto.boardSection.UpdateBoardDTO;
import capstone.sangcom.entity.dao.board.BoardDAO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

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
    // 게시글 저장
    public int insert(BoardDAO boardDAO) {
        String query = "INSERT INTO " + BOARD_TABLE + " (title, body, user_id, type) VALUES (:title, :body, :user_id, :type)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", boardDAO.getTitle())
                .addValue("body", boardDAO.getBody())
                .addValue("user_id", boardDAO.getUser_id())
                .addValue("type", boardDAO.getType());

        KeyHolder key = new GeneratedKeyHolder();
        if (jdbcTemplate.update(query, params, key, new String[]{"board_id"}) != 1)
            return -1;

        return key.getKey().intValue();
    }


    @Override
    // 검색어에 해당하는 게시글 목록 검색
    public List<BoardDTO> findBoard(String type, String keyword) {
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
    public List<String> findUser(String boardId) {
        String query = "SELECT user_id FROM " + BOARD_TABLE + " WHERE board_id = :board_id";
        return jdbcTemplate.query(query,
                new MapSqlParameterSource()
                        .addValue("board_id", boardId),
                ((rs, rowNum) -> rs.getString("user_id")));
    }

    @Override
    // 게시글 종류에 해당하는 모든 게시글 조회
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
    // 하나의 게시글 읽어오기
    public BoardDetailDTO readBoard(String userId, int boardId) {
        String query = "SELECT " + BOARD_TABLE + ".*, " +
                "(SELECT COUNT(board_id) FROM " + BOARD_GOOD_TABLE + " WHERE " + BOARD_TABLE + ".board_id=" + BOARD_GOOD_TABLE + ".board_id) AS goodCount, " +
                "(SELECT COUNT(board_id) FROM " + REPLY_TABLE + " WHERE " + BOARD_TABLE + ".board_id=" + REPLY_TABLE + ".board_id) AS replyCount, " +
                "(SELECT COUNT(board_id) FROM " + SCRAP_TABLE + " WHERE " + BOARD_TABLE + ".board_id=" + SCRAP_TABLE + ".board_id) AS scrapCount, " +
                "IF((SELECT COUNT(board_id) FROM " + BOARD_GOOD_TABLE + " WHERE user_id=:user_id AND " + BOARD_GOOD_TABLE + ".board_id=" + BOARD_TABLE + ".board_id) > 0, 'Y', 'N') AS goodCheck, " +
                "IF((SELECT COUNT(board_id) FROM " + SCRAP_TABLE + " WHERE user_id=:user_id AND " + SCRAP_TABLE + ".board_id=" + BOARD_TABLE + ".board_id) > 0, 'Y', 'N') AS scrapCheck " +
                "FROM " + BOARD_TABLE + " WHERE board_id =:board_id";

//        if((select count(board_id) from boardgood WHERE user_id=? AND boardgood.board_id=board.board_id) > 0 , 'Y', 'N') as goodCheck,
//        if((select count(board_id) from scrap WHERE user_id=? AND scrap.board_id=board.board_id) > 0 , 'Y', 'N') as scrapCheck
//        from board where board_id = ?

        HashMap<String, Object> params = new HashMap<>();
        params.put("user_id", userId);
        params.put("board_id", boardId);

        BoardDetailDTO boardDetail = jdbcTemplate.queryForObject(query, params, boardDetailRowMapper);
        if (boardDetail == null) return null;

        if (userId.equals(boardDetail.getUser_id()))
            boardDetail.setUserCheck("Y");

        return boardDetail;
    }

    @Override
    public List<BoardDTO> readBoardWithReply(String userId) {
        String query = "SELECT " + BOARD_TABLE + ".*, " +
                "(SELECT COUNT(board_id) FROM " + BOARD_GOOD_TABLE + " WHERE " + BOARD_TABLE + ".board_id = " + BOARD_GOOD_TABLE + ".board_id) AS goodCount, " +
                "(SELECT COUNT(board_id) FROM " + REPLY_TABLE + " WHERE " + BOARD_TABLE + ".board_id = " + REPLY_TABLE + ".board_id) AS replyCount, " +
                "(SELECT COUNT(board_id) FROM " + SCRAP_TABLE + " WHERE " + BOARD_TABLE + ".board_id = " + SCRAP_TABLE + ".board_id) AS scrapCount" +
                "FROM (SELECT DISTINCT board_id FROM " + REPLY_TABLE + " WHERE user_id = :user_id) AS MR INNER JOIN " + BOARD_TABLE + " ON MR.board_id = " + BOARD_TABLE + ".board_id " +
                "ORDER BY regdate DESC";

        return jdbcTemplate.query(query,
                new MapSqlParameterSource()
                        .addValue("user_id", userId),
                boardRowMapper);
    }

    @Override
    // 게시글의 제목과 본문 수정(이미지 제외)
    public boolean updateBoard(int boardId, UpdateBoardDTO updateBoardDTO) {
        String query = "UPDATE " + BOARD_TABLE + " SET title = :title, body = :body WHERE board_id = :board_id";
        Map<String, Object> params = new HashMap<>();
        params.put("title", updateBoardDTO.getTitle());
        params.put("body", updateBoardDTO.getBody());
        params.put("board_id", boardId);

        int update = jdbcTemplate.update(query, params);
        System.out.println(update);
        if(update != 1)
            return false;
        else
            return true;
    }

    @Override
    // 게시글 삭제
    public boolean deleteBoard(int boardId) {
        String query = "DELETE FROM " + BOARD_TABLE + " WHERE board_id = :board_id";
        if(jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("board_id", boardId)) != 1)
            return false;
        else
            return true;
    }

    @Override
    // 해당 게시글이 유저가 작성한 것인지 확인하기
    public boolean isUserWriteBoard(int boardId, String userId) {
        String query = "SELECT user_id FROM " + BOARD_TABLE + " WHERE board_id = :board_id AND user_id = :user_id";
        HashMap<String, Object> params = new HashMap<>();
        params.put("board_id", boardId);
        params.put("user_id", userId);

        List<String> result = jdbcTemplate.query(query, params,
                (rs, rowNum) -> rs.getString("user_id")
        );

        if(result.isEmpty())
            return true;
        else
            return false;
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
