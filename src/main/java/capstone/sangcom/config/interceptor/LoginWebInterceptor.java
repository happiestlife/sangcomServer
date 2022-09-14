package capstone.sangcom.config.interceptor;

import capstone.sangcom.util.auth.JwtUtils;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginWebInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();

        String token = findJwtCookie(cookies);
        if(token == null || JwtUtils.isValidToken(token) == false) {
            response.sendRedirect("/auth/refresh");
            return false;
        }

        request.setAttribute("user", JwtUtils.getUserFromToken(token));

        return true;
    }

    private String findJwtCookie(Cookie[] cookies) {
        if(cookies == null)
            return null;

        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("acc")) {
                return cookie.getValue();
            }
        }

        return null;
    }

}