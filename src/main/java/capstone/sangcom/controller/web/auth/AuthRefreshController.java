package capstone.sangcom.controller.web.auth;

import capstone.sangcom.service.token.TokenService;
import capstone.sangcom.util.auth.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthRefreshController {

    private final TokenService tokenService;

    @GetMapping("/auth/refresh")
    public String refreshToken(@CookieValue(required = false) String ref,
                               HttpServletRequest request,
                               HttpServletResponse response) throws URISyntaxException {
        if(ref == null)
            return "redirect:/login";

        // 1. refresh 토큰이 유효한 토큰인지 확인하고 저장했던 토큰과 같은 토큰인지 검증
        String access = tokenService.createNewTokenWithRefreshToken(ref);
        if(access == null)
            return "redirect:/login";

        // 3. 쿠키에 access 토큰 담아서 전송
        Cookie acc = makeCookie(access, "acc", JwtUtils.ACCESS_EXPIRE_TIME);
        response.addCookie(acc);

        // 4. 이전 페이지로 이동
        String lastPageURI = request.getHeader("Referer");
        if(lastPageURI == null)
            return "redirect:/";
        else {
            URI uri = new URI(lastPageURI);
            String path = uri.getPath();
            return "redirect:/" + path.substring(1);
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
