package capstone.sangcom.controller.api;

import capstone.sangcom.dto.login.MailDTO;
import capstone.sangcom.service.user.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/mail")
@RequiredArgsConstructor
public class FeedbackController {
    private final EmailService emailService;

    @GetMapping("/send")
    public String sendMailMain(){
        return "<html>"; // html명 넣기
    }

    @PostMapping("/send")
    public String sendMail(MailDTO mailDTO){
        emailService.sendFeedback(mailDTO);
        System.out.println("메일 전송 완료");
        return "<html>"; // html명 넣기
    }
}
