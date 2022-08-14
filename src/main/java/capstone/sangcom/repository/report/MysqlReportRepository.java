package capstone.sangcom.repository.report;

import capstone.sangcom.dto.boardSection.BoardDTO;
import capstone.sangcom.dto.boardSection.BoardDetailDTO;
import capstone.sangcom.dto.reportSection.ReplyReportDTO;
import capstone.sangcom.dto.reportSection.ReportDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Repository
public class MysqlReportRepository implements ReportRepository{

    private final String BOARD_REPORT_TABLE = "boardreport";

    private final RowMapper<ReportDTO> reportDTORowMapper;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MysqlReportRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        jdbcTemplate = namedParameterJdbcTemplate;
        reportDTORowMapper = new ReportRowMapper();
    }

    @Override
    // 자신이 신고한 신고목록 조회(게시판)
    public ReportDTO getMyReport(String userId) {
        String query = "SELECT * FROM " + BOARD_REPORT_TABLE + " WHERE send_id= :user_id";

        HashMap<String, Object> params = new HashMap<>();
        params.put("user_id", userId);

        ReportDTO reportDTO = jdbcTemplate.queryForObject(query, params, reportDTORowMapper);
        if(reportDTO == null) return null;

        return reportDTO;
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
}
