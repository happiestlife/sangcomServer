package capstone.sangcom.service.report;

import capstone.sangcom.dto.reportSection.ReadReportDTO;
import capstone.sangcom.dto.reportSection.ReportDTO;
import capstone.sangcom.repository.report.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

    private final ReportRepository reportRepository;

    @Override
    @Transactional
    public ReadReportDTO getMyReport(String userId) {
        ReportDTO reportDTO = reportRepository.getMyReport(userId);

        return new ReadReportDTO(reportDTO);
    }
}
