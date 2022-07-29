package capstone.sangcom.dto.boardSection;

import capstone.sangcom.repository.dao.board.BoardDAO;
import lombok.Data;

@Data
public class BoardDTO {

    /**
     * BoardDAO로 refactoring 필요
     */
    private final int board_id;
    private final String title;
    private final String body;
    private final String user_id;
    private final String regdate;
    private final String type;
    /**
     * ------------------------------
     */
    private final int goodCount;
    private final int replyCount;
    private final int scrapCount;

}
