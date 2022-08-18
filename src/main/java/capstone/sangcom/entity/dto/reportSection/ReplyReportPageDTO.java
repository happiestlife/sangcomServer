package capstone.sangcom.entity.dto.reportSection;

import lombok.Data;

@Data
public class ReplyReportPageDTO {
    private final int report_id;
    private final int reply_id;
    private final String send_id;
    private final String recv_id;
    private final String body;
    private final String regdate;
}
