package capstone.sangcom.entity.dto.userSection.info;

import capstone.sangcom.entity.UserRole;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class ProfileDTO{

    private String id;
    private String name;
    private String phone;
    private Integer schoolgrade;
    private Integer schoolclass;
    private Integer schoolnumber;
    private Integer year;
    private String birth;
    private String email;
    private UserRole role;

    public ProfileDTO() {
        role = UserRole.STUDENT;
    }
}