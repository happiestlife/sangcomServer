package capstone.sangcom.controller.api.response.reply;

import capstone.sangcom.dto.reportSection.ReplyReportDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ReplyReportResponse {
    private boolean success;
    private List<ReplyReportDTO> data;
}
