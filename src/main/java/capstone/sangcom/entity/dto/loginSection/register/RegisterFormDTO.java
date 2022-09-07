package capstone.sangcom.entity.dto.loginSection.register;

import capstone.sangcom.entity.UserRole;
import lombok.*;

@Data
public class RegisterFormDTO {
    private String id;
    private String password;
    private String passwordCheck;
    private String name;
    private String phone;
    private Integer schoolgrade;
    private Integer schoolclass;
    private Integer schoolnumber;
    private UserRole role;
    private Integer year;
    private String birth;
    private String email;

    public RegisterFormDTO() {
        role = UserRole.STUDENT;
    }
}