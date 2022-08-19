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

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

    private final ReportRepository reportRepository;

    @Override
    @Transactional
    public List<ReportDTO> getMyReport(String userId) {
        return reportRepository.getMyReport(userId);
    }

    @Override
    public boolean reportBoard(PostReportBoardDTO postReportBoardDTO) {
        reportRepository.reportBoard(new ReportBoardDAO(1, postReportBoardDTO.getBoard_id(), "asd",postReportBoardDTO.getRevc_id(), postReportBoardDTO.getBody(), "das"));
        return true;
    }
}
