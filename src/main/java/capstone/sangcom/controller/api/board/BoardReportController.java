package capstone.sangcom.controller.api.board;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.controller.api.response.reply.ReportResponse;
import capstone.sangcom.entity.dto.reportSection.PostReportBoardDTO;
import capstone.sangcom.entity.dto.reportSection.ReportDTO;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.service.report.board.BoardReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/board/report")
@RequiredArgsConstructor
public class BoardReportController {

    private final BoardReportService boardReportService;
    /**
     * 자신이 신고한 신고목록 조회(게시판)
     * GET
     * /api/board/report/me
     * */
    @GetMapping("/me")
    public ResponseEntity<ReportResponse> getMyReport(HttpServletRequest request){
        JwtUser user = (JwtUser) request.getAttribute("user");
        List<ReportDTO> reportDTO = boardReportService.getMyReport(user.getId());

        return ResponseEntity
                .ok(new ReportResponse(true, reportDTO));

    }
    /**
     * 게시판 신고 요청하기
     * POST
     * /api/board/report
     * */
    @PostMapping
    public ResponseEntity<SimpleResponse> reportBoard(HttpServletRequest request, @RequestBody PostReportBoardDTO postReportBoardDTO){
        JwtUser user = (JwtUser) request.getAttribute("user");
        if(boardReportService.reportBoard(user.getId(), postReportBoardDTO)){
            return ResponseEntity.ok(new SimpleResponse(true));
        }else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new SimpleResponse(false));
        }
    }

}
