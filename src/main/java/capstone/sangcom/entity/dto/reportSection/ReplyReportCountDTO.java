package capstone.sangcom.entity.dto.reportSection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReplyReportCountDTO {
    private final String recv_id;
    private final int count;
}
