package capstone.sangcom.entity.dto.replySection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyDTO {

    private final int replyId;
    private final String body;
    private final String userId;
    private final String regdate;
    private final int boardId;
    private final int parentId;
    private final int level;

    private final int goodCount;
    private final String goodCheck;
    private String userCheck;
}
