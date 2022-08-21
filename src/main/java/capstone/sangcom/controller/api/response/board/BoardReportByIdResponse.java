package capstone.sangcom.controller.api.response.board;


import capstone.sangcom.entity.dto.reportSection.ReportDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BoardReportByIdResponse {
    private boolean success;
    private List<ReportDTO> board;
}
