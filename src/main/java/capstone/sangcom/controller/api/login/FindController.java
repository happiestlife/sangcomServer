package capstone.sangcom.controller.api.login;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
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

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class FindController {

    private final UserService userService;

    private final MailService mailService;

    private final StudentAuthService studentAuthService;

    /**
     * 비밀번호 찾기
     */
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
     * 아이디 찾기
     */

}
