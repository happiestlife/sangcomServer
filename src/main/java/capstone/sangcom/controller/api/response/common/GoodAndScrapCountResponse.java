package capstone.sangcom.controller.api.response.common;

import lombok.Data;

@Data
public class GoodAndScrapCountResponse {
    private final boolean success;
    private final int goodCount;
}
