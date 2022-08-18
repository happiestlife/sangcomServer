package capstone.sangcom.service.board.reply;

import capstone.sangcom.entity.dto.replySection.ReplyCreateDTO;
import capstone.sangcom.entity.dto.replySection.ReplyDTO;
import capstone.sangcom.entity.UserRole;
import capstone.sangcom.entity.dto.replySection.ReplyTreeDTO;

import java.util.List;

public interface ReplyService {

    public List<ReplyTreeDTO> readReply(String userId, int boardId);

    public ReplyDTO createReplyAtBoard(ReplyCreateDTO replyCreateDTO);

    public ReplyDTO createReplyAtReply(ReplyCreateDTO replyCreateDTO);

    public boolean updateReply(String body, int replyId, String userId);

    public boolean deleteReply(UserRole role, String userId, int replyId);
}
