package capstone.sangcom.controller.web.login;

import capstone.sangcom.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class FindPasswordController {

    private final LoginService loginService;

    @GetMapping("/find/password")
    public String getFindPasswordPage() {
        return "login/findPW";
    }

//    @PostMapping("/find/password")

}
