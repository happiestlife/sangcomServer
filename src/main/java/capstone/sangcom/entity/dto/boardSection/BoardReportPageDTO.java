package capstone.sangcom.entity.dto.boardSection;

import lombok.Data;

@Data
public class BoardReportPageDTO {
    private final int report_id;
    private final int board_id;
    private final String send_id;
    private final String recv_id;
    private final String body;
    private final String regdate;
}
