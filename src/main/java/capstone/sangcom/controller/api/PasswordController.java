package capstone.sangcom.controller.api;

import capstone.sangcom.dto.login.SetPasswordDTO;
import capstone.sangcom.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PasswordController {
    private final UserService userService;

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
