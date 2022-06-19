package capstone.sangcom.dto.login;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class SetPasswordDTO {
    private String password;
    private String newPassword;
    private String newPasswordConfirm;
}
