package capstone.sangcom.controller.api.response.user;

import capstone.sangcom.entity.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UserResponse {

    private boolean success;
    private UserDTO data;

}

