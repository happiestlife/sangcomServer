package capstone.sangcom.service.report.reply;

import capstone.sangcom.entity.dto.reportSection.PostReplyReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReplyReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReplyReportPageDTO;

import java.util.List;

public interface ReplyReportService {
    public List<ReplyReportDTO> getMyReplyReport(String userId);
    public boolean reportReply(String userId, PostReplyReportDTO postReplyReportDTO);
    public List<ReplyReportDTO> getReplyReportById(String recvId);
    public List<ReplyReportPageDTO> getReplyReport(int page);
}
