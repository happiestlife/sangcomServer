package capstone.sangcom.controller.api.reply;

import capstone.sangcom.controller.api.response.reply.ReplyReportResponse;
import capstone.sangcom.dto.reportSection.ReadReplyReportDTO;
import capstone.sangcom.dto.reportSection.ReplyReportDTO;
import capstone.sangcom.dto.reportSection.ReportDTO;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.service.report.ReplyReportService;
import capstone.sangcom.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
