package capstone.sangcom.controller.api.response.reply;

import capstone.sangcom.entity.dto.reportSection.ReplyReportCountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ReplyReportCountResponse {
    private boolean success;
    private List<ReplyReportCountDTO> data;
}
