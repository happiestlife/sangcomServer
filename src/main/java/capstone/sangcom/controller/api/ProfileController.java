package capstone.sangcom.controller.api;

import capstone.sangcom.dto.login.UpdateUserInfoDTO;
import capstone.sangcom.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;

    /**
     * 회원정보수정
     * */
    @PutMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserInfo(String id, @RequestBody UpdateUserInfoDTO updateUserInfoDTO) throws Exception {
        userService.editUserInfo(id, updateUserInfoDTO);
    }
}
