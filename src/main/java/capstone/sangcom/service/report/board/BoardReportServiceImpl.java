package capstone.sangcom.service.report.board;

import capstone.sangcom.entity.dao.replyReport.ReportBoardDAO;
import capstone.sangcom.entity.dto.boardSection.BoardReportDTO;
import capstone.sangcom.entity.dto.boardSection.BoardReportPageDTO;
import capstone.sangcom.entity.dto.reportSection.PostReportBoardDTO;
import capstone.sangcom.entity.dto.reportSection.ReportDTO;
import capstone.sangcom.repository.report.boardReport.BoardReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardReportServiceImpl implements BoardReportService {

    private final BoardReportRepository reportRepository;

    @Override
    @Transactional
    public List<ReportDTO> getMyReport(String userId) {
        return reportRepository.getMyReport(userId);
    }

    @Override
    @Transactional
    public boolean reportBoard(String userId, PostReportBoardDTO postReportBoardDTO) {
        reportRepository.reportBoard(new ReportBoardDAO(1, postReportBoardDTO.getBoard_id(), userId,postReportBoardDTO.getRecv_id(), postReportBoardDTO.getBody(), "das"));
        return true;
    }

    @Override
    @Transactional
    public List<BoardReportDTO> countReportById() {
        return reportRepository.countReportById();
    }

    @Override
    @Transactional
    public List<ReportDTO> getReportById(String id) {
        return reportRepository.getReportById(id);
    }

    @Override
    public List<BoardReportPageDTO> getReport(int page) {
        return reportRepository.getReport(page);
    }
}
