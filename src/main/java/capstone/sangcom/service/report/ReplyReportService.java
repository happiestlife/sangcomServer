package capstone.sangcom.service.report;

import capstone.sangcom.dto.reportSection.ReadReplyReportDTO;

public interface ReplyReportService {
    public ReadReplyReportDTO getMyReplyReport(String userId);
}
