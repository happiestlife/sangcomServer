package capstone.sangcom.service.reply;

import capstone.sangcom.entity.dto.replySection.ReplyCreateDTO;
import capstone.sangcom.entity.dto.replySection.ReplyDTO;
import capstone.sangcom.entity.UserRole;
import capstone.sangcom.entity.dto.replySection.ReplyTreeDTO;
import capstone.sangcom.repository.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;

    @Override
    public List<ReplyTreeDTO> readReply(String userId, int boardId) {
        List<ReplyDTO> allReplies = replyRepository.getAllReplies(userId, boardId);
        for (ReplyDTO reply : allReplies) {
            if(reply.getUserId().equals(userId))
                reply.setUserCheck("Y");
        }

        List<ReplyTreeDTO> replyTree = new ArrayList<>();
        int count = -1;
        for (int i = 0; i < allReplies.size(); i++) {
            ReplyDTO reply = allReplies.get(i);
            if (reply.getLevel() == 0) {
                replyTree.add(new ReplyTreeDTO(reply, new ArrayList<>()));
                count++;
            } else {
                if(count != -1)
                    replyTree.get(count).getChild().add(reply);
            }
        }

        return replyTree;
    }

    @Override
    public ReplyDTO createReplyAtBoard(ReplyCreateDTO replyCreateDTO) {
        int createdReplyId = replyRepository.create(replyCreateDTO);
        if(createdReplyId == -1)
            return null;

        if(!replyRepository.update(createdReplyId, createdReplyId))
            return null;

        return getReplyData(replyCreateDTO, createdReplyId);
    }

    @Override
    public ReplyDTO createReplyAtReply(ReplyCreateDTO replyCreateDTO) {
        List<String> check = replyRepository.getUserIdsAtReply(replyCreateDTO.getParentId());
        if(check.isEmpty())
            return null;

        int createdReplyId = replyRepository.create(replyCreateDTO);
        if(createdReplyId == -1)
            return null;

        return getReplyData(replyCreateDTO, createdReplyId);
    }

    @Override
    public boolean updateReply(String body, int replyId, String userId) {
        if(!checkUserMadeReply(replyId, userId))
            return false;

        if(replyRepository.update(body, replyId))
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteReply(UserRole role, String userId, int replyId) {
        if(!checkUserMadeReply(replyId, userId))
            return false;

        return replyRepository.delete(replyId, replyId);
    }

    private ReplyDTO getReplyData(ReplyCreateDTO replyCreateDTO, int createdReplyId) {
        ReplyDTO oneReply = replyRepository.getOneReply(replyCreateDTO.getUserId(), replyCreateDTO.getBoardId(), createdReplyId);
        if(oneReply == null)
            return null;

        oneReply.setUserCheck("Y");

        List<String> userIds = replyRepository.getUserIdsAtBoard(replyCreateDTO.getBoardId());
        // firebase로 유저 아이디에 해당하는 기기에 메세지 보내기

        return oneReply;
    }

    private boolean checkUserMadeReply(int replyId, String userId) {
        return !replyRepository.getReplyCreateByUserId(replyId, userId).isEmpty();

    }

}
