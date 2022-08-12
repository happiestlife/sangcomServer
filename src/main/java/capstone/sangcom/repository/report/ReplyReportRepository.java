package capstone.sangcom.repository.report;

import capstone.sangcom.dto.reportSection.ReplyReportDTO;

public interface ReplyReportRepository {
    public ReplyReportDTO getMyReplyReport(String userId);
}
