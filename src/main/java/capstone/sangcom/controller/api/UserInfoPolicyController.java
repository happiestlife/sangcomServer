package capstone.sangcom.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//개인정보 처리방침 조회 API
@RestController
public class UserInfoPolicyController {
    @RequestMapping("/privacypolicy") // 현재 path는 임의 지정
    public String privacyPolicy(){
        return "<html>"; // html명 넣기
    }
}
