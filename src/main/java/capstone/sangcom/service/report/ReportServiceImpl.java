package capstone.sangcom.service.report;

import capstone.sangcom.entity.dao.replyReport.ReportBoardDAO;
import capstone.sangcom.entity.dto.reportSection.PostReportBoardDTO;
import capstone.sangcom.entity.dto.reportSection.ReadReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReportDTO;
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

    @Override
    public boolean reportBoard(PostReportBoardDTO postReportBoardDTO) {
        reportRepository.reportBoard(new ReportBoardDAO(1, postReportBoardDTO.getBoard_id(), "asd",postReportBoardDTO.getRevc_id(), postReportBoardDTO.getBody(), "das"));
        return true;
    }
}
