package capstone.sangcom.entity.dto.reportSection;

import lombok.Data;

@Data
public class PostReportBoardDTO {
    private final int board_id;
    private final String recv_id;
    private final String body;
}
