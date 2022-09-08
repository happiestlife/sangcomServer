package capstone.sangcom.controller.web.login;

import capstone.sangcom.controller.api.response.login.LoginResponse;
import capstone.sangcom.entity.dto.loginSection.login.LoginDTO;
import capstone.sangcom.service.login.LoginService;
import capstone.sangcom.util.auth.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login/login";
    }

    @PostMapping("/login")
    public String login(LoginDTO loginDTO,
                        HttpServletResponse response) {
        LoginResponse rs = loginService.login(loginDTO);
        if (rs != null) {
            Cookie accessCookie = makeCookie(rs.getToken().getAccess_token(), "acc", JwtUtils.ACCESS_EXPIRE_TIME);
            Cookie refreshCookie = makeCookie(rs.getToken().getRefresh_token(), "ref", JwtUtils.REFRESH_EXPIRE_TIME);

            response.addCookie(accessCookie);
            response.addCookie(refreshCookie);

            return "redirect:";
        } else {
            return "login/login";
        }
    }

    private Cookie makeCookie(String token, String name, int expire) {
        Cookie cookie = new Cookie(name, token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(expire);
        cookie.setPath("/");

        return cookie;
    }
}
