package capstone.sangcom.service.report;

import capstone.sangcom.entity.dto.reportSection.PostReplyReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReadReplyReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReplyReportPageDTO;

import java.util.List;

public interface ReplyReportService {
    public ReadReplyReportDTO getMyReplyReport(String userId);
    public boolean reportReply(PostReplyReportDTO postReplyReportDTO);
    public List<ReplyReportPageDTO> getReplyReport();
}
