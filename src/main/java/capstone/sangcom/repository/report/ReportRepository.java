package capstone.sangcom.repository.report;

import capstone.sangcom.entity.dao.replyReport.ReportBoardDAO;
import capstone.sangcom.entity.dto.reportSection.ReportDTO;

public interface ReportRepository {
    public ReportDTO getMyReport(String userId);
    public int reportBoard(ReportBoardDAO reportBoardDAO);
}
