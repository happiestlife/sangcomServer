package capstone.sangcom.controller.api.login;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.controller.api.response.login.LoginResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.service.login.LoginService;
import capstone.sangcom.service.user.UserService;
import capstone.sangcom.entity.dto.loginSection.login.LoginDTO;
import capstone.sangcom.service.token.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class LoginApiController {

    private final LoginService userService;
    private final UserService user2Service;

    private final TokenService tokenService;

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


    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public ResponseEntity<SimpleResponse> logout(HttpServletRequest request) {
        JwtUser user = (JwtUser) request.getAttribute("user");

        if(tokenService.delete(user.getId()))
            return ResponseEntity.
                    ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SimpleResponse(false));
    }

    /**
     * 회원 탈퇴
     */
    @DeleteMapping("/quit")
    public ResponseEntity<SimpleResponse> userOut(HttpServletRequest request){
        JwtUser user = (JwtUser) request.getAttribute("user");

        if (user2Service.leave(user.getId()))
            return ResponseEntity.
                    ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
    }
}
