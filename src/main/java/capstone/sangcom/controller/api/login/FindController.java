package capstone.sangcom.controller.api.login;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.loginSection.find.FindPasswordDTO;
import capstone.sangcom.entity.dto.mailSection.MailInfoDTO;
import capstone.sangcom.entity.dto.userSection.auth.AuthStudentDTO;
import capstone.sangcom.entity.User;
import capstone.sangcom.service.auth.student.StudentAuthService;
import capstone.sangcom.service.mail.MailService;
import capstone.sangcom.service.user.UserService;
import capstone.sangcom.util.login.TempPasswordUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class FindController {

    private final UserService userService;

    private final MailService mailService;

    private final StudentAuthService studentAuthService;

    /**
     * 임시 비밀번호 발급
     * POST
     * /api/user/password/find
     * */
    @Transactional
    @PostMapping("/password/find")
    public ResponseEntity<SimpleResponse> findPassword(@RequestBody FindPasswordDTO findPasswordDTO) {
        if (!studentAuthService.studentAuthorization(new AuthStudentDTO(findPasswordDTO.getStudentId(), findPasswordDTO.getName())))
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new SimpleResponse(false));

        String id = findPasswordDTO.getId();

        User user = userService.findById(id);
        String tmpPassword = TempPasswordUtils.makeTemporaryPassword();

        if(user == null || !userService.editPassword(id, tmpPassword))
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new SimpleResponse(false));

        mailService.sendMail(new MailInfoDTO(user.getEmail(), MailService.PASSWORD_SUBJECT, user.getId() + "님의 임시 비밀번호는 " + tmpPassword + "입니다.\n감사합니다."));

        return ResponseEntity
                .ok(new SimpleResponse(true));
    }

    /**
     * 패스워드 수정(로그인 필요)
     * POST
     * /api/user/password/change
     * */
    @PostMapping("/password/change")
    public ResponseEntity<SimpleResponse> setPassword(HttpServletRequest request, @RequestBody String password) {
        JwtUser user = (JwtUser) request.getAttribute("user");
        if (userService.editPassword(user.getId(), password))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SimpleResponse(false));
    }



    /**
     * 패스워드 일치 여부 확인(로그인 필요)
     * POST
     * /api/user/password/check
     * */
    @PostMapping("/password/check")
    public ResponseEntity<SimpleResponse> checkPassword(HttpServletRequest request,@RequestBody String password) {
        JwtUser user = (JwtUser) request.getAttribute("user");
        if (userService.checkPassword(user.getId(), password))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SimpleResponse(false));
    }


    /**
     * 아이디 찾기
     */

}
