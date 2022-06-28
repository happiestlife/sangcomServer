package capstone.sangcom.controller.api.login;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.controller.api.response.login.LoginResponse;
import capstone.sangcom.controller.api.response.login.TokenResponse;
import capstone.sangcom.dto.login.ConfirmIdDTO;
import capstone.sangcom.dto.login.LoginDTO;
import capstone.sangcom.entity.User;
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
     * 회원가입
     */
    @PostMapping("/register")
    public ResponseEntity<SimpleResponse> register(@RequestBody User user) {
        if (userService.findById(user.getId()) != null)
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST).
                    body(new SimpleResponse(false));

        if (userService.register(user) == null)
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST).
                    body(new SimpleResponse(false));

        return ResponseEntity.
                ok(new SimpleResponse(true));

    }

    /**
     * 회원가입 중 아이디 중복 확인
     */
    @PostMapping("/confirm/name")
    public ResponseEntity<SimpleResponse> confirmDupId(@RequestBody ConfirmIdDTO confirmIdDTO) {
        if (userService.findById(confirmIdDTO.getId()) != null)
            return ResponseEntity.
                    ok(new SimpleResponse(true));
        else
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST).
                    body(new SimpleResponse(false));
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) {
        TokenResponse token = userService.login(loginDTO);
        if(token == null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new LoginResponse("false", null));
        else
            return ResponseEntity.
                    ok(new LoginResponse("true", token));
    }

    /**
     * 비밀번호 찾기
     */

    /**
     * 아이디 찾기
     */

}
