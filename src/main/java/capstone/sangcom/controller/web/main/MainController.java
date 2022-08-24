package capstone.sangcom.controller.web.main;

import capstone.sangcom.entity.JwtUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping
    public String getMainPage(HttpServletRequest request, Model model) {
        JwtUser user = (JwtUser) request.getAttribute("user");
        model.addAttribute("user", user);

        return "main";
    }
}
