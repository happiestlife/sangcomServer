package capstone.sangcom.controller.api.login;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.entity.dto.userSection.auth.AuthStudentDTO;
import capstone.sangcom.entity.dto.loginSection.register.ConfirmIdDTO;
import capstone.sangcom.entity.User;
import capstone.sangcom.service.auth.student.StudentAuthService;
import capstone.sangcom.service.login.LoginService;
import capstone.sangcom.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class RegisterApiController {

    private final UserService userService;

    private final LoginService loginService;

    private final StudentAuthService studentAuthService;

    /**
     * 회원가입
     */
    @PostMapping("/register")
    public ResponseEntity<SimpleResponse> register(@RequestBody User user) {
        if (userService.findById(user.getId()) != null)
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST).
                    body(new SimpleResponse(false));

        if (loginService.register(user) == null)
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
     * 회원가입 중 학번 인증
     */
    @PostMapping("/auth/student/check")
    public ResponseEntity<SimpleResponse> confirmStudentAuth(@RequestBody AuthStudentDTO authStudentDTO) {
        if(studentAuthService.studentAuthorization(authStudentDTO))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new SimpleResponse(false));
    }
}
