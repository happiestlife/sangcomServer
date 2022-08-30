package capstone.sangcom.controller.api.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShowImageResponse {

    private final boolean success;
    private final String path;
}
