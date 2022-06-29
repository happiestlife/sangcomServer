package capstone.sangcom.entity;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User{

    private String id;
    private String password;
    private String name;
    private String phone;
    private Integer schoolgrade;
    private Integer schoolclass;
    private Integer schoolnumber;
    private UserRole role;
    private Integer year;
    private String birth;
    private String email;

}