package capstone.sangcom.repository.report.replyReport;

import capstone.sangcom.entity.dto.reportSection.ReplyReportCountDTO;
import capstone.sangcom.entity.dto.reportSection.ReplyReportDTO;
import capstone.sangcom.entity.dao.replyReport.ReplyReportDAO;
import capstone.sangcom.entity.dto.reportSection.ReplyReportPageDTO;

import java.util.List;

public interface ReplyReportRepository {
    public List<ReplyReportDTO> getMyReplyReport(String userId);
    public int replyReport(ReplyReportDAO replyReportDAO);
    public List<ReplyReportCountDTO> countReplyReportById();
    public List<ReplyReportDTO> getReplyReportById(String recvId);
    public List<ReplyReportPageDTO> getReplyReport(int page);
}
