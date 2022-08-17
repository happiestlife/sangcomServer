package capstone.sangcom.service.report;

import capstone.sangcom.entity.dto.reportSection.PostReplyReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReadReplyReportDTO;

public interface ReplyReportService {
    public ReadReplyReportDTO getMyReplyReport(String userId);
    public boolean reportReply(PostReplyReportDTO postReplyReportDTO);
}
