package capstone.sangcom.controller.api.reply;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.controller.api.response.reply.ReplyReportPageResponse;
import capstone.sangcom.controller.api.response.reply.ReplyReportResponse;
import capstone.sangcom.entity.dto.reportSection.PostReplyReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReadReplyReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReplyReportDTO;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.reportSection.ReplyReportPageDTO;
import capstone.sangcom.service.report.ReplyReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reply/report")
@RequiredArgsConstructor
public class ReplyReportController {

    private final ReplyReportService replyReportService;
    /**
     * 자신이 신고한 신고목록 조회(댓글)
     * GET
     * /api/reply/report/me
     * */
    @GetMapping("/me")
    public ResponseEntity<ReplyReportResponse> getMyReplyReport(HttpServletRequest request){
        JwtUser user = (JwtUser) request.getAttribute("user");
        List<ReplyReportDTO> replyReportDTO = replyReportService.getMyReplyReport(user.getId());

        return ResponseEntity
                .ok(new ReplyReportResponse(true, replyReportDTO));

    }

    /**
     * 댓글 신고 요청하기
     * POST
     * /api/reply/report
     * */
    @PostMapping
    public ResponseEntity<SimpleResponse> reportReply(HttpServletRequest request, @RequestBody PostReplyReportDTO postReplyReportDTO){
        JwtUser user = (JwtUser) request.getAttribute("user");

        if(replyReportService.reportReply(user.getId(), postReplyReportDTO)){
            return ResponseEntity.ok(new SimpleResponse(true));
        }else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new SimpleResponse(false));
        }
    }

    /**
     * id 값에 따른 댓글 신고 목록 조회 ( 댓글, Master권한 )
     * GET
     * /api/reply/report?id={신고받은 id값}
     * */
    @GetMapping
    public ResponseEntity<ReplyReportResponse> getReplyReportById(@RequestParam String id){ // api 명세서가 잘못된느낌?
        System.out.println(id);
        List<ReplyReportDTO> replyReportDTOS = replyReportService.getReplyReportById(id);

        return ResponseEntity.ok(new ReplyReportResponse(true, replyReportDTOS));
    }

    /**
     * 댓글 신고 조회 (master만 조회 - 전체 데이터 조회 ver)
     * GET
     * /api/reply/report/:page
     * */
    @GetMapping("/{page}")
    public ResponseEntity<ReplyReportPageResponse> getReplyReport(@PathVariable int page){
        List<ReplyReportPageDTO> replyReportPageDTOS = replyReportService.getReplyReport(page);

        if(replyReportPageDTOS != null){
            return ResponseEntity
                    .ok(new ReplyReportPageResponse(true, replyReportPageDTOS));
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }
}
