package capstone.sangcom.dto.boardSection;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UpdateBoardDTO {

    private final String title;
    private final String body;
    private final List<MultipartFile> images;

}
