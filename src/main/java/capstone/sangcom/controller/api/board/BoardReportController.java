package capstone.sangcom.controller.api.board;

import capstone.sangcom.controller.api.response.board.BoardReportByIdResponse;
import capstone.sangcom.controller.api.response.board.BoardReportPageResponse;
import capstone.sangcom.controller.api.response.board.BoardReportResponse;
import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.controller.api.response.reply.ReportResponse;
import capstone.sangcom.entity.dto.boardSection.BoardReportDTO;
import capstone.sangcom.entity.dto.boardSection.BoardReportPageDTO;
import capstone.sangcom.entity.dto.reportSection.*;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.service.report.board.BoardReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    /**
     * 신고당한 아이디 및 횟수 ( 게시판, Master권한)
     * GET
     * /api/board/report/count
     * */
    @GetMapping("/count")
    public ResponseEntity<BoardReportResponse> countReportById(){
        List<BoardReportDTO> boardReportDTOS = boardReportService.countReportById();

        return ResponseEntity.ok(new BoardReportResponse(true, boardReportDTOS));
    }

    /**
     * id값에 따른 게시판 신고 목록 조회 ( 게시판, Master권한 )
     * GET
     * /api/board/report?id={신고받은 id값}
     * */
    @GetMapping
    public ResponseEntity<BoardReportByIdResponse> getReportById(@RequestParam String id){
        List<ReportDTO> reportDTOSs = boardReportService.getReportById(id);

        if (reportDTOSs.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BoardReportByIdResponse(false, null));
        }

        try{
            List<ReportDTO> reportDTOS = boardReportService.getReportById(id);
            return ResponseEntity.ok(new BoardReportByIdResponse(true, reportDTOS));
        }catch (NullPointerException nullPointerException){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BoardReportByIdResponse(false, null));
        }
//        if(reportDTOS.isEmpty()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BoardReportByIdResponse(false, null));
//        }
//
//        if(reportDTOS != null){
//            return ResponseEntity.ok(new BoardReportByIdResponse(true, reportDTOS));
//        } else{
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new BoardReportByIdResponse(false, null));
//        }
    }

    /**
     * 게시판 신고 조회 (master만 조회 - 전체 데이터 조회 ver)
     * GET
     * /api/board/report/{page번호}
     * */
    @GetMapping("/{page}")
    public ResponseEntity<BoardReportPageResponse> getReport(@PathVariable int page){
        List<BoardReportPageDTO> boardReportPageDTOS = boardReportService.getReport(page);

        if (boardReportPageDTOS.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BoardReportPageResponse(false, null));
        }

        return ResponseEntity.ok(new BoardReportPageResponse(true, boardReportPageDTOS));
    }

}
