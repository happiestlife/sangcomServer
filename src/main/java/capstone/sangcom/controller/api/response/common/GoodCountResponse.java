package capstone.sangcom.controller.api.response.common;

import lombok.Data;

@Data
public class GoodCountResponse {
    private final boolean success;
    private final int goodCount;
}
