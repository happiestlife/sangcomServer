package capstone.sangcom.config.interceptor;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.util.auth.JwtUtils;
import capstone.sangcom.entity.JwtUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwt;
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

        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(mapper.writeValueAsString(new SimpleResponse(false)));

        return false;
    }
}
