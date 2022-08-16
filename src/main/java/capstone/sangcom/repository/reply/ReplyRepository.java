package capstone.sangcom.repository.reply;

import capstone.sangcom.entity.dto.replySection.ReplyCreateDTO;
import capstone.sangcom.entity.dto.replySection.ReplyDTO;

import java.util.List;

public interface ReplyRepository {

    public int create(ReplyCreateDTO replyCreateDTO);

    public int getReplyCount(int boardId);

    public List<String> getReplyCreateByUserId(int replyId, String userId);

    public List<String> getUserIdsAtBoard(int boardId);

    public List<String> getUserIdsAtReply(int parentReplyId);

    public ReplyDTO getOneReply(String userId, int boardId, int replyId);

    public List<ReplyDTO> getAllReplies(String userId, int boardId);

    public boolean update(int parentId, int replyId);

    public boolean update(String body, int replyId);

    public boolean delete(int replyId, int parentId);
}
