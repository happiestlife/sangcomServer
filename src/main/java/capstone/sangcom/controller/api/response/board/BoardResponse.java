package capstone.sangcom.controller.api.response.board;

import capstone.sangcom.dto.boardSection.BoardDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class BoardResponse {

    private boolean success;
    private List<BoardDTO> data;

}
