package capstone.sangcom.controller.api.response.user;

import capstone.sangcom.entity.UserDTO;
import capstone.sangcom.entity.dto.userSection.info.ProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UserResponse {

    private boolean success;
    private ProfileDTO data;

}