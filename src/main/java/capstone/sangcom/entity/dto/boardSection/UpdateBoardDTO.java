package capstone.sangcom.entity.dto.boardSection;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
public class UpdateBoardDTO {

    private String title;
    private String body;
    private List<MultipartFile> images;

}
