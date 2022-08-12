package capstone.sangcom.repository.report;

import capstone.sangcom.dto.reportSection.ReplyReportDTO;
import capstone.sangcom.dto.reportSection.ReportDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Repository
public class MysqlReplyReportRepository implements ReplyReportRepository{

    private final String REPLY_REPORT_TABLE = "replyreport";

    private final RowMapper<ReplyReportDTO> replyReportDTORowMapper;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MysqlReplyReportRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        jdbcTemplate = namedParameterJdbcTemplate;
        replyReportDTORowMapper = new ReplyReportRowMapper();
    }
    @Override
    public ReplyReportDTO getMyReplyReport(String userId) {
        String query = "SELECT * FROM" + REPLY_REPORT_TABLE + "WHERE send_id= :id";

        HashMap<String, Object> params = new HashMap<>();
        params.put("user_id", userId);

        ReplyReportDTO replyReportDTO = jdbcTemplate.queryForObject(query, params, replyReportDTORowMapper);
        if(replyReportDTO == null) return null;

        return replyReportDTO;
    }

    private class ReplyReportRowMapper implements RowMapper<ReplyReportDTO>{

        @Override
        public ReplyReportDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ReplyReportDTO(
                    rs.getInt("reply_id"),
                    rs.getString("recv_id"),
                    rs.getString("send_id"),
                    rs.getString("body"),
                    rs.getString("regdate"));
        }
    }
}
