package capstone.sangcom.entity.dto.tokenSection;

import capstone.sangcom.entity.JwtUser;
import lombok.Data;

@Data
public class TokenValidateDTO {

    private final boolean success;
    private final JwtUser data;

}
