package capstone.sangcom.controller.api.Feedback;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final MailService mailService;

    /**
     * 개발자에게 피드백
     * POST
     * /api/feedback
     * */
    @Transactional
    @PostMapping
    public ResponseEntity<SimpleResponse> postFeedback(HttpServletRequest request){
        JwtUser user = (JwtUser) request.getAttribute("user");
        return null;
    }
}
