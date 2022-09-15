package capstone.sangcom.controller.web.user;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.controller.api.response.user.ShowImageResponse;
import capstone.sangcom.controller.api.response.user.UserResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.userSection.info.ProfileDTO;
import capstone.sangcom.entity.dto.userSection.info.ProfileFileDTO;
import capstone.sangcom.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProfileWebController {
    private final UserService userService;

    @GetMapping("/board")
    public String getUserInfoPage(Model model){
        model.addAttribute("profileDTO", new ProfileDTO());
        return "board/myPage";
    }

    /**
     * 회원정보보기
     * */
    @GetMapping("/info")
    public String getUserInfo(HttpServletRequest request, Model model){
        JwtUser user = (JwtUser) request.getAttribute("user");
        ProfileDTO data = userService.getUserInfo(user.getId());

        return "user/myPage";
    }

//    @PostMapping("/profile")
//    public String imageUpload(HttpServletRequest request, ProfileFileDTO file){
//        JwtUser user = (JwtUser) request.getAttribute("user");
//        List<MultipartFile> images = file.getProfile();
//        if(user)
//    }

    @GetMapping("/profile")
    public String showImage(@RequestParam String id, Model model){
        // String paths = userService.showImage(id);

        model.addAttribute("id", id);

        return "user/myPage";
    }







}
