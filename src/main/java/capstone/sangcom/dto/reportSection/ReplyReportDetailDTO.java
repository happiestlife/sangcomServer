package capstone.sangcom.dto.reportSection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReplyReportDetailDTO {

    private final int report_id;
    private final int board_id;
    private final int reply_id;
    private final String send_id;
    private final String revc_id;
    private final String body;
    private final String regdate;

}
