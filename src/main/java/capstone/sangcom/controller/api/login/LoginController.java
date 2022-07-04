package capstone.sangcom.controller.api.login;

import capstone.sangcom.controller.api.response.login.LoginResponse;
import capstone.sangcom.dto.loginSection.login.LoginDTO;
import capstone.sangcom.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) {
        LoginResponse response = userService.login(loginDTO);
        if (response == null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        else
            return ResponseEntity.
                    ok(response);
    }
}
