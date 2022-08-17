package capstone.sangcom.service.report;

import capstone.sangcom.entity.dto.reportSection.PostReportBoardDTO;
import capstone.sangcom.entity.dto.reportSection.ReadReportDTO;

public interface ReportService {
    public ReadReportDTO getMyReport(String userId);
    public boolean reportBoard(PostReportBoardDTO postReportBoardDTO);
}
