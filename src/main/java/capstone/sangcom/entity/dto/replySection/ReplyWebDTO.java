package capstone.sangcom.entity.dto.replySection;

import lombok.Data;

@Data
public class ReplyWebDTO {
    private final int replyId;
    private final String body;
    private final String regdate;
    private final int boardId;
    private final int parentId;
    private final int level;

    private final String userId;
    private String profilePath;

    private final int goodCount;
    private final String goodCheck;
    private String userCheck;

    public ReplyWebDTO(ReplyDTO replyDTO) {
        this.replyId = replyDTO.getReplyId();
        this.body = replyDTO.getBody();
        this.regdate = replyDTO.getRegdate();
        this.boardId = replyDTO.getBoardId();
        this.parentId = replyDTO.getParentId();
        this.level = replyDTO.getLevel();
        this.userId = replyDTO.getUserId();
        this.goodCount = replyDTO.getGoodCount();
        this.goodCheck = replyDTO.getGoodCheck();
    }
}
