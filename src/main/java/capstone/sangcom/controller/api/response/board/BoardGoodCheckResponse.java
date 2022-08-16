package capstone.sangcom.controller.api.response.board;

import lombok.Data;

@Data
public class BoardGoodCheckResponse {
    private final boolean success;
    private final String stat;
}
