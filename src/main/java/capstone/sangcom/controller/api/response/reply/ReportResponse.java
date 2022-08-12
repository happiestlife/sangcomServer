package capstone.sangcom.controller.api.response.reply;
import capstone.sangcom.dto.reportSection.ReportDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ReportResponse {
    private boolean success;
    private List<ReportDTO> data;
}
