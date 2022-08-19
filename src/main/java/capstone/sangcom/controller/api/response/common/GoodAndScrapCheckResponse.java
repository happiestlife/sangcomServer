package capstone.sangcom.controller.api.response.common;

import lombok.Data;

@Data
public class GoodAndScrapCheckResponse {
    private final boolean success;
    private final String stat;
}
