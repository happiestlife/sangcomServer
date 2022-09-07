package capstone.sangcom.controller.web.login;

import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.repository.token.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LogoutController {

    private final TokenRepository tokenRepository;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
            HttpServletResponse response) {
        JwtUser user = (JwtUser) request.getAttribute("user");

        if (tokenRepository.deleteById(user.getId())) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }

            return "redirect:/login";
        } else {
            return "main";
        }
    }
}
