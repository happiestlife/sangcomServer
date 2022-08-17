package capstone.sangcom.controller.api.reply;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.controller.api.response.reply.ReplyReportResponse;
import capstone.sangcom.entity.dto.reportSection.PostReplyReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReadReplyReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReplyReportDTO;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.service.report.ReplyReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

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
        ReadReplyReportDTO readReplyReportDTO = replyReportService.getMyReplyReport(user.getId());

        ArrayList<ReplyReportDTO> replyReportDTO = new ArrayList<>();
        replyReportDTO.add(readReplyReportDTO.getReplyReportDTO());

        return ResponseEntity
                .ok(new ReplyReportResponse(true, replyReportDTO));

    }

    /**
     * 댓글 신고 요청하기
     * Post
     * /api/reply/report
     * */
    @PostMapping
    public ResponseEntity<SimpleResponse> reportReply(@RequestBody PostReplyReportDTO postReplyReportDTO){
        if(replyReportService.reportReply(postReplyReportDTO)){
            return ResponseEntity.ok(new SimpleResponse(true));
        }else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new SimpleResponse(false));
        }
    }

}
