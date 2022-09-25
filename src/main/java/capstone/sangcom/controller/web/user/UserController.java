package capstone.sangcom.controller.web.user;

import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.userSection.info.ProfileDTO;
import capstone.sangcom.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getMyPage(HttpServletRequest request) {
        JwtUser user = (JwtUser) request.getAttribute("user");

        ProfileDTO userInfo = userService.getUserInfo(user.getId());
        return "user/myPage";
    }

    @GetMapping("/edit")
    public String getEditUserInfo() {
        return "user/changeUserInfo";
    }
}
