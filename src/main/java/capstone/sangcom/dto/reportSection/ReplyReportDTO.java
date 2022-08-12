package capstone.sangcom.dto.reportSection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReplyReportDTO {

    private final int reply_id;
    private final String recv_id;
    private final String send_id;
    private final String body;
    private final String regdate;
}
