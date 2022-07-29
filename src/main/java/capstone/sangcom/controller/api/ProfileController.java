package capstone.sangcom.controller.api;

import capstone.sangcom.dto.userSection.info.UpdateUserInfoDTO;
import capstone.sangcom.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;


//    /**
//     * 회원정보보기
//     * */
//    @GetMapping("/info")
//    public void getUserInfo(HttpSession httpSession, Model model) throws Exception{
//        String id = (String) httpSession.getAttribute("id");
//        User user = serv
//
//    }

    /**
     * 회원정보수정
     * */
    @PutMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserInfo(String id, @RequestBody UpdateUserInfoDTO updateUserInfoDTO) throws Exception {
        userService.editUserInfo(id, updateUserInfoDTO);
    }
}
