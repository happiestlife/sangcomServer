package capstone.sangcom.config.interceptor;

import capstone.sangcom.util.auth.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();

        String token = findJwtCookie(cookies);
        if(token == null) {
            FailHeader.setResponse(response);

            return false;
        }

        request.setAttribute("user", JwtUtils.getUserFromToken(token));

        return true;
    }

    private String findJwtCookie(Cookie[] cookies) {
        if(cookies == null)
            return null;

        String token = null;
        for (Cookie cookie : cookies) {
            if(cookie.getName() == "auth")
                token = JwtUtils.getTokenFromHeader(cookie.getValue());
            if(JwtUtils.isValidToken(token))
                return token;
        }

        return null;
    }

}
