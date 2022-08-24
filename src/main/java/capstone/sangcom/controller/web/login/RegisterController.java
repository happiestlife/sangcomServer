package capstone.sangcom.controller.web.login;

import capstone.sangcom.entity.User;
import capstone.sangcom.entity.dto.loginSection.register.RegisterFormDTO;
import capstone.sangcom.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final LoginService loginService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new RegisterFormDTO());
        return "login/register";
    }

    @PostMapping("/register")
    public String register(User user) {
        if(loginService.register(user) == null)
            return "login/register";
        else
            return "redirect:login";
    }

}
