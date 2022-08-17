package capstone.sangcom.controller.api;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.controller.api.response.user.UserResponse;

import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.UserDTO;
import capstone.sangcom.entity.dto.userSection.info.UpdateUserInfoDTO;
import capstone.sangcom.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
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
    @PutMapping("/infos")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SimpleResponse> updateUserInfo(HttpServletRequest request, @RequestBody UpdateUserInfoDTO updateUserInfoDTO){
        JwtUser user = (JwtUser) request.getAttribute("user");

        if (userService.editUserInfo(user.getId(), updateUserInfoDTO))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SimpleResponse(false));
    }
}
