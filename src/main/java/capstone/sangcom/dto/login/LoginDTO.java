package capstone.sangcom.dto.login;

import lombok.*;

@Getter // 인스턴스 변수를 반환한다.
@Setter // 인스턴스 변수를 대입하거나 수정한다.
@NoArgsConstructor
@AllArgsConstructor
@ToString // 객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴한다.
public class LoginDTO {
    private String id;
    private String password;
}

