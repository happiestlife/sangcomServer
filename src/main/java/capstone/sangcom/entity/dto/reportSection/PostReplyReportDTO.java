package capstone.sangcom.entity.dto.reportSection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PostReplyReportDTO {
    private final int board_id;
    private final int reply_id;
    private final String recv_id;
    private final String body;
}
