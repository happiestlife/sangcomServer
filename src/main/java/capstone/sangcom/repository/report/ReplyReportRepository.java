package capstone.sangcom.repository.report;

import capstone.sangcom.entity.dto.reportSection.ReplyReportDTO;
import capstone.sangcom.entity.dao.replyReport.ReplyReportDAO;

public interface ReplyReportRepository {
    public ReplyReportDTO getMyReplyReport(String userId);
    public int replyReport(ReplyReportDAO replyReportDAO);
}
