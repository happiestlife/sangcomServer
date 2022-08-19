package capstone.sangcom.controller.api.response.reply;

import capstone.sangcom.entity.dto.reportSection.ReplyReportPageDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ReplyReportPageResponse {
    private boolean success;
    private List<ReplyReportPageDTO> data;
}
