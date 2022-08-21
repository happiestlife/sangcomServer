package capstone.sangcom.repository.report.boardReport;

import capstone.sangcom.entity.dao.replyReport.ReportBoardDAO;
import capstone.sangcom.entity.dto.boardSection.BoardReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReportDTO;

import java.util.List;

public interface BoardReportRepository {
    public List<ReportDTO> getMyReport(String userId);
    public int reportBoard(ReportBoardDAO reportBoardDAO);
    public List<BoardReportDTO> countReportById();
    public List<ReportDTO> getReportById(String id);
}
