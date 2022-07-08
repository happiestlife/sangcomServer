package capstone.sangcom.repository.dao.board;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BoardDAO {

    private final int board_id;
    private final String title;
    private final String body;
    private final String user_id;
    private final String regdate;
    private final String type;

}
