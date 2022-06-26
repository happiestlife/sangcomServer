package capstone.sangcom.controller.api;

import capstone.sangcom.dto.login.FindPasswordDTO;
import capstone.sangcom.dto.login.MailDTO;
import capstone.sangcom.dto.login.SetPasswordDTO;
import capstone.sangcom.entity.User;
import capstone.sangcom.service.user.EmailService;
import capstone.sangcom.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PasswordController {
    private final UserService userService;
    private final EmailService emailService;

    /**
     * 유저 정보 확인
     */
    @GetMapping("/password/find") //user의 정보가 맞는지 체크하는 컨트롤러
    public @ResponseBody Map<String, Boolean> forgotPassword(FindPasswordDTO findPasswordDTO){
        Map<String, Boolean> json = new HashMap<>();
        boolean pwFindCheck = userService.userCheck(findPasswordDTO);

        System.out.println(pwFindCheck);
        json.put("check", pwFindCheck);
        return json;
    }

    /**
     * 임시 비밀번호 전송
     */
    @PostMapping("/password/find/sendmail") //등록된 이메일로 임시비밀번호 발송하고 발송된 임시 비밀번호로 사용자의 pw를 변경하는 컨트롤러
    public @ResponseBody void sendMailer(User user){
        MailDTO mailDTO = emailService.createMailAndChangePassword(user);
        emailService.mailSend(mailDTO);
    }


    /**
     * 비밀번호 수정
     */
    @PostMapping("/password/change")
    public String updatePassword(String id, @Valid SetPasswordDTO setPasswordDTO,
                                 Errors errors, Model model, RedirectAttributes attributes){
        // 에러
        if(errors.hasErrors()){
            model.addAttribute(id);
            return "/password/change";
        }
        //비밀번호 변경 성공
        userService.editPassword(id, setPasswordDTO.getNewPassword());
        attributes.addFlashAttribute("message", "패스워드를 변경했습니다.");
        return "redirect:" + "/password/change";
    }

}
