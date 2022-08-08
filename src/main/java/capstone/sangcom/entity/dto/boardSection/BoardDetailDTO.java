package capstone.sangcom.entity.dto.boardSection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardDetailDTO {

    /**
     * BoardDTO로 refactoring 필요
     */
    private final int board_id;
    private final String title;
    private final String body;
    private final String user_id;
    private final String regdate;
    private final String type;
    private final int goodCount;
    private final int replyCount;
    private final int scrapCount;
    /**
     * ------------------------------
     */
    private String goodCheck;
    private String scrapCheck;
    private String userCheck;

}
