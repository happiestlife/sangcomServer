package capstone.sangcom.repository.report;

import capstone.sangcom.entity.dto.reportSection.ReplyReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReplyReportDetailDTO;
import capstone.sangcom.entity.dao.replyReport.ReplyReportDAO;
import capstone.sangcom.entity.dto.reportSection.ReplyReportPageDTO;
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
public class MysqlReplyReportRepository implements ReplyReportRepository{

    private final String REPLY_REPORT_TABLE = "replyreport";

    private final RowMapper<ReplyReportDTO> replyReportDTORowMapper;

    private final RowMapper<ReplyReportPageDTO> replyReportPageRowMapper;

//    private final RowMapper<ReplyReportDetailDTO> replyReportDetailDTORowMapper;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MysqlReplyReportRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        jdbcTemplate = namedParameterJdbcTemplate;
//        replyReportDetailDTORowMapper = new ReplyReportDetailRowMapper();
        replyReportDTORowMapper = new ReplyReportRowMapper();
        replyReportPageRowMapper = new ReplyReportPageRowMapper();
    }
    @Override
    public ReplyReportDTO getMyReplyReport(String userId) {
        String query = "SELECT * FROM " + REPLY_REPORT_TABLE + "WHERE send_id= :user_id";

        HashMap<String, Object> params = new HashMap<>();
        params.put("user_id", userId);

        ReplyReportDTO replyReportDTO = jdbcTemplate.queryForObject(query, params, replyReportDTORowMapper);
        if(replyReportDTO == null) return null;

        return replyReportDTO;
    }

    @Override
    public int replyReport(ReplyReportDAO replyReportDAO) {
        String query = "INSERT INTO " + REPLY_REPORT_TABLE + " (board_id, reply_id, recv_id, send_id, body) VALUES (:board_id, :reply_id, :recv_id, :send_id, :body)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("board_id", replyReportDAO.getBoard_id())
                .addValue("reply_id", replyReportDAO.getReply_id())
                .addValue("recv_id", replyReportDAO.getRevc_id())
                .addValue("send_id", replyReportDAO.getSend_id())
                .addValue("body", replyReportDAO.getBody());

        KeyHolder key = new GeneratedKeyHolder();
        if(jdbcTemplate.update(query, params, key, new String[]{"report_id"}) != 1)
            return -1;

        return key.getKey().intValue();
    }

    @Override
    public List<ReplyReportDTO> getReplyReportById(String recvId) {
        String query = "SELECT * FROM " + REPLY_REPORT_TABLE + " WHERE recv_id = :recv_id";
        Map<String, Object> params = new HashMap<>();
        params.put("recv_id", recvId);

        return jdbcTemplate.query(query, params, replyReportDTORowMapper);
    }

    @Override
    public List<ReplyReportPageDTO> getReplyReport() {
        String query = "SELECT * FROM " + REPLY_REPORT_TABLE + "ORDER BY regdate DESC, report_id DESC ";//LIMIT (?, 10)
        List<ReplyReportPageDTO> rrp = jdbcTemplate.query(query, replyReportPageRowMapper);

        if(rrp.size() != 0)
            return rrp;
        else
            return null;
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

    private class ReplyReportPageRowMapper implements RowMapper<ReplyReportPageDTO>{
        @Override
        public ReplyReportPageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ReplyReportPageDTO(
                    rs.getInt("report_id"),
                    rs.getInt("reply_id"),
                    rs.getString("send_id"),
                    rs.getString("recv_id"),
                    rs.getString("body"),
                    rs.getString("regdate"));
        }
    }


//    private class ReplyReportDetailRowMapper implements RowMapper<ReplyReportDetailDTO>{
//
//        @Override
//        public ReplyReportDetailDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return new ReplyReportDetailDTO(
//                    rs.getInt("report_id"),
//                    rs.getInt("board_id"),
//                    rs.getInt("reply_id"),
//                    rs.getString("send_id"),
//                    rs.getString("recv_id"),
//                    rs.getString("body"),
//                    rs.getString("regdate"));
//        }
//
//    }
}
