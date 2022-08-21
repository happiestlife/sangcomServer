package capstone.sangcom.controller.api.response.board;

import capstone.sangcom.entity.dto.boardSection.BoardReportPageDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class BoardReportPageResponse {
    private boolean success;
    private List<BoardReportPageDTO> board;
}
