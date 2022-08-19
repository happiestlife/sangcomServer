package capstone.sangcom.repository.report;

import capstone.sangcom.entity.dao.replyReport.ReportBoardDAO;
import capstone.sangcom.entity.dto.reportSection.ReportDTO;

import java.util.List;

public interface ReportRepository {
    public List<ReportDTO> getMyReport(String userId);
    public int reportBoard(ReportBoardDAO reportBoardDAO);
}
