package capstone.sangcom.repository.report.boardReport;

import capstone.sangcom.entity.dao.replyReport.ReportBoardDAO;
import capstone.sangcom.entity.dto.boardSection.BoardReportDTO;
import capstone.sangcom.entity.dto.boardSection.BoardReportPageDTO;
import capstone.sangcom.entity.dto.reportSection.ReportDTO;
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
public class MysqlBoardReportRepository implements BoardReportRepository {

    private final String BOARD_REPORT_TABLE = "boardreport";

    private final RowMapper<ReportDTO> reportDTORowMapper;

    private final RowMapper<BoardReportDTO> reportCountRowMapper;

    private final RowMapper<BoardReportPageDTO> boardReportPageRowMapper;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MysqlBoardReportRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        jdbcTemplate = namedParameterJdbcTemplate;
        reportDTORowMapper = new ReportRowMapper();
        reportCountRowMapper = new ReportCountRowMapper();
        boardReportPageRowMapper = new BoardReportPageRowMapper();
    }

    @Override
    // 자신이 신고한 신고목록 조회(게시판)
    public List<ReportDTO> getMyReport(String userId) {
        String query = "SELECT * FROM " + BOARD_REPORT_TABLE + " WHERE send_id=:user_id";

        HashMap<String, Object> params = new HashMap<>();
        params.put("user_id", userId);

        return jdbcTemplate.query(query, params, reportDTORowMapper);
    }

    @Override
    public int reportBoard(ReportBoardDAO reportBoardDAO) {
        String query = "INSERT INTO " + BOARD_REPORT_TABLE + " (board_id, recv_id, send_id, body) VALUES (:board_id, :recv_id, :send_id, :body)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("board_id", reportBoardDAO.getBoard_id())
                .addValue("recv_id", reportBoardDAO.getRecv_id())
                .addValue("send_id", reportBoardDAO.getSend_id())
                .addValue("body", reportBoardDAO.getBody());

        KeyHolder key = new GeneratedKeyHolder();
        if(jdbcTemplate.update(query, params, key, new String[]{"report_id"}) != 1)
            return -1;

        return key.getKey().intValue();
    }

    @Override
    public List<BoardReportDTO> countReportById() {
        String query = "SELECT recv_id, COUNT(*) AS countss FROM " + BOARD_REPORT_TABLE + " GROUP BY recv_id ";

        return jdbcTemplate.query(query, reportCountRowMapper);
    }

    @Override
    public List<ReportDTO> getReportById(String id) {
        String query = "SELECT * FROM " + BOARD_REPORT_TABLE + " WHERE recv_id =:recv_id";
        Map<String, Object> params = new HashMap<>();
        params.put("recv_id", id);

        return jdbcTemplate.query(query, params, reportDTORowMapper);
    }

    @Override
    public List<BoardReportPageDTO> getReport(int page) {
        String query = "SELECT * FROM " + BOARD_REPORT_TABLE + " ORDER BY regdate DESC, report_id DESC LIMIT :page, 10";

        Map<String, Object> params = new HashMap<>();
        params.put("page", (page - 1) * 10);

        return jdbcTemplate.query(query, params, boardReportPageRowMapper);
    }

    private class ReportRowMapper implements RowMapper<ReportDTO>{
        @Override
        public ReportDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ReportDTO(
                    rs.getInt("board_id"),
                    rs.getString("recv_id"),
                    rs.getString("send_id"),
                    rs.getString("body"),
                    rs.getString("regdate"));
        }
    }

    private class ReportCountRowMapper implements RowMapper<BoardReportDTO> {
        @Override
        public BoardReportDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BoardReportDTO(
                    rs.getString("recv_id"),
                    rs.getInt("countss"));
        }
    }

    private class BoardReportPageRowMapper implements RowMapper<BoardReportPageDTO>{
        @Override
        public BoardReportPageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BoardReportPageDTO(
                    rs.getInt("report_id"),
                    rs.getInt("board_id"),
                    rs.getString("send_id"),
                    rs.getString("recv_id"),
                    rs.getString("body"),
                    rs.getString("regdate"));
        }
    }
}
