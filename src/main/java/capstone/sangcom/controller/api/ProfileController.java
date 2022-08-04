package capstone.sangcom.controller.api;

import capstone.sangcom.controller.api.response.user.UserResponse;
import capstone.sangcom.dto.userSection.info.UpdateUserInfoDTO;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.UserDTO;
import capstone.sangcom.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;


    /**
     * 회원정보보기
     * */
    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserInfo(HttpServletRequest request){
        JwtUser user = (JwtUser) request.getAttribute("user");
        UserDTO user1 = userService.getUserInfo(user.getId());

        return ResponseEntity.ok(new UserResponse(true, user1));
    }

    /**
     * 회원정보수정
     * */
    @PutMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserInfo(String id, @RequestBody UpdateUserInfoDTO updateUserInfoDTO) throws Exception {
        userService.editUserInfo(id, updateUserInfoDTO);
    }
}
