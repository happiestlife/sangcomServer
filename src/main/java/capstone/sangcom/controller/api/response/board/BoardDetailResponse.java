package capstone.sangcom.controller.api.response.board;

import capstone.sangcom.entity.dto.boardSection.BoardDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@AllArgsConstructor
public class BoardDetailResponse {

    private boolean success;
    private List<BoardDetailDTO> data;
    private List<String> imagepath;

}
