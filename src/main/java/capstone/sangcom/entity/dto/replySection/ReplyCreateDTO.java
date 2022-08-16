package capstone.sangcom.entity.dto.replySection;

import lombok.Data;

@Data
public class ReplyCreateDTO {

    private final String body;
    private final String userId;
    private final int boardId;
    private final int parentId;
    private final int level;

}
