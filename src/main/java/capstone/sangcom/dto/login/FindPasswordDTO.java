package capstone.sangcom.dto.login;


import lombok.Data;

// @Data는 callSuper, includeFieldName, exclude와 같은 파라미터와 같이 사용될 수 없다.
// @Data = @Getter/Setter + @ToString + @EqualsAndHashCode + @RequiredArgsConstructor
@Data
public class FindPasswordDTO {

    private final String id;
    private final String name;
    private final String studentid;

}
