package capstone.sangcom.config.interceptor;

import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.UserRole;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        JwtUser user = (JwtUser) request.getAttribute("user");
        if(!user.getRole().equals(UserRole.ADMIN)){
            FailHeader.setResponse(response);

            return false;
        }

        return true;
    }
}
