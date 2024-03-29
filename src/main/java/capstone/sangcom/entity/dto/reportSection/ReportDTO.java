package capstone.sangcom.entity.dto.reportSection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReportDTO {

    private final int board_id;
    private final String recv_id;
    private final String send_id;
    private final String body;
    private final String regdate;
}
