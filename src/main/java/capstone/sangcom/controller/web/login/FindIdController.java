package capstone.sangcom.controller.web.login;

import capstone.sangcom.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FindIdController {

    private final LoginService loginService;

    @GetMapping("/find/id")
    public String getFindPasswordPage() {
        return "login/findId";
    }

//    @PostMapping("/find/password")

}
