package capstone.sangcom.service.report;

import capstone.sangcom.entity.dto.reportSection.*;

import java.util.List;

public interface ReplyReportService {
    public List<ReplyReportDTO> getMyReplyReport(String userId);
    public boolean reportReply(String userId, PostReplyReportDTO postReplyReportDTO);
    public List<ReplyReportCountDTO> countReplyReportById();
    public List<ReplyReportDTO> getReplyReportById(String recvId);
    public List<ReplyReportPageDTO> getReplyReport(int page);
}
