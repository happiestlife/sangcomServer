package capstone.sangcom.service.report.board;

import capstone.sangcom.entity.dto.boardSection.BoardReportDTO;
import capstone.sangcom.entity.dto.boardSection.BoardReportPageDTO;
import capstone.sangcom.entity.dto.reportSection.PostReportBoardDTO;
import capstone.sangcom.entity.dto.reportSection.ReportDTO;

import java.util.List;

public interface BoardReportService {
    public List<ReportDTO> getMyReport(String userId);
    public boolean reportBoard(String userId, PostReportBoardDTO postReportBoardDTO);
    public List<BoardReportDTO> countReportById();
    public List<ReportDTO> getReportById(String id);
    public List<BoardReportPageDTO> getReport(int page);
}
