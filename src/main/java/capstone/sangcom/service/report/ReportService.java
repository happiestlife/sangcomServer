package capstone.sangcom.service.report;

import capstone.sangcom.dto.reportSection.ReadReportDTO;

public interface ReportService {
    public ReadReportDTO getMyReport(String userId);
}
