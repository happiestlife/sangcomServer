package capstone.sangcom.controller.api.response.board;

import capstone.sangcom.entity.dto.boardSection.BoardReportDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BoardReportResponse {
    private boolean success;
    private List<BoardReportDTO> data;
}
