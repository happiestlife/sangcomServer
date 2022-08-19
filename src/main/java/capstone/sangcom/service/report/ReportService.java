package capstone.sangcom.service.report;

import capstone.sangcom.entity.dto.reportSection.PostReportBoardDTO;
import capstone.sangcom.entity.dto.reportSection.ReadReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReportDTO;

import java.util.List;

public interface ReportService {
    public List<ReportDTO> getMyReport(String userId);
    public boolean reportBoard(PostReportBoardDTO postReportBoardDTO);
}
