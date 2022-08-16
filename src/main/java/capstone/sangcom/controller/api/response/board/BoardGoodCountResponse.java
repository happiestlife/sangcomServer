package capstone.sangcom.controller.api.response.board;

import lombok.Data;

@Data
public class BoardGoodCountResponse {
    private final boolean success;
    private final int goodCount;
}
