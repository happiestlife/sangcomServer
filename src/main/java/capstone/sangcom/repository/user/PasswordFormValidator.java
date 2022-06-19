package capstone.sangcom.repository.user;

import capstone.sangcom.dto.login.SetPasswordDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordFormValidator implements Validator {
    // Password 와 PasswordConfirm 값이 일치하는지 확인해주는 validator 생성
    @Override
    public boolean supports(Class<?> clazz) {
        return SetPasswordDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SetPasswordDTO setPasswordDTO = (SetPasswordDTO) target;
        if(!setPasswordDTO.getNewPassword().equals(setPasswordDTO.getNewPasswordConfirm())){
            errors.rejectValue("newPassword", "wrong value", "입력한 새 패스워드가 일치하지 않습니다.");
        }
    }
}
