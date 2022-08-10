package capstone.sangcom.repository.report;

import capstone.sangcom.dto.reportSection.ReportDTO;

public interface ReportRepository {
    public ReportDTO getMyReport(String userId);
}
