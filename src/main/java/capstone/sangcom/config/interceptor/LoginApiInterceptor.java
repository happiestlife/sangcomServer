package capstone.sangcom.config.interceptor;

import capstone.sangcom.util.auth.JwtUtils;
import capstone.sangcom.entity.JwtUser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginApiInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("Authorization");
        if (header != null) {
            String token = JwtUtils.getTokenFromHeader(header);
            if (JwtUtils.isValidToken(token)) {
                JwtUser user = JwtUtils.getUserFromToken(token);
                request.setAttribute("user", user);

                return true;
            }
        }

        FailHeader.setResponse(response);

        return false;
    }
}
