package capstone.sangcom.controller.api.reply;

import capstone.sangcom.controller.api.response.reply.ReportResponse;
import capstone.sangcom.dto.reportSection.ReadReportDTO;
import capstone.sangcom.dto.reportSection.ReportDTO;
import capstone.sangcom.entity.JwtUser;
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
@RequestMapping("/api/board/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    /**
     * 자신이 신고한 신고목록 조회(게시판)
     * GET
     * /api/board/report/me
     * */
    @GetMapping("/me")
    public ResponseEntity<ReportResponse> getMyReport(HttpServletRequest request){
        JwtUser user = (JwtUser) request.getAttribute("user");
        ReadReportDTO readReportDTO = reportService.getMyReport(user.getId());

        ArrayList<ReportDTO> reportDTO = new ArrayList<>();
        reportDTO.add(readReportDTO.getReportDTO());

        return ResponseEntity
                .ok(new ReportResponse(true, reportDTO));

    }

}
