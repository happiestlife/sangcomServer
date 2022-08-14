package capstone.sangcom.repository.report;

import capstone.sangcom.dto.reportSection.ReplyReportDTO;
import capstone.sangcom.repository.dao.replyReport.ReplyReportDAO;

public interface ReplyReportRepository {
    public ReplyReportDTO getMyReplyReport(String userId);
    public int replyReport(ReplyReportDAO replyReportDAO);
}
