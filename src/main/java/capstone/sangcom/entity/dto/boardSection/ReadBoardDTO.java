package capstone.sangcom.entity.dto.boardSection;

import lombok.Data;

import java.util.List;

@Data
public class ReadBoardDTO {

    private final BoardDetailDTO boardDetail;
    private final List<String> paths;

}
