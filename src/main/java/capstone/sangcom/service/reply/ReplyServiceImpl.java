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
        // 게시판 아이디에 달린 모든 댓글을 가져온다.
        List<ReplyDTO> allReplies = replyRepository.getAllReplies(userId, boardId);
        for (ReplyDTO reply : allReplies) {
            if(reply.getUserId().equals(userId))
                reply.setUserCheck("Y");
        }

        // 댓글을 부모 - 자식 관계의 트리 형태로 가져온다.
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
        // 댓글 생성
        int createdReplyId = replyRepository.create(replyCreateDTO);
        if(createdReplyId == -1)
            return null;

        // 댓글의 부모 댓글을 자기 자신으로 설정
        if(!replyRepository.update(createdReplyId, createdReplyId))
            return null;

        return getReplyData(replyCreateDTO, createdReplyId);
    }

    @Override
    public ReplyDTO createReplyAtReply(ReplyCreateDTO replyCreateDTO) {
        // 지정한 부모 댓글이 존재하는지 확인
        List<String> check = replyRepository.getUserIdsAtReply(replyCreateDTO.getParentId());
        if(check.isEmpty())
            return null;

        // 댓글 생성. 댓글이 DB에 추가되지 않았다면 실패로 간주
        int createdReplyId = replyRepository.create(replyCreateDTO);
        if(createdReplyId == -1)
            return null;

        return getReplyData(replyCreateDTO, createdReplyId);
    }

    @Override
    public boolean updateReply(String body, int replyId, String userId) {
        // 유저가 해당 댓글을 작성했는지 확인
        if(!checkUserMadeReply(replyId, userId))
            return false;

        return replyRepository.update(body, replyId);
    }

    @Override
    public boolean deleteReply(UserRole role, String userId, int replyId) {
        // 유저가 해당 댓글을 작성했는지 확인
        if(!checkUserMadeReply(replyId, userId))
            return false;

        return replyRepository.delete(replyId, replyId);
    }

    private ReplyDTO getReplyData(ReplyCreateDTO replyCreateDTO, int createdReplyId) {
        // 특정 게시판에 달린 특정 댓글 가져오기
        ReplyDTO oneReply = replyRepository.getOneReply(replyCreateDTO.getUserId(), replyCreateDTO.getBoardId(), createdReplyId);
        if(oneReply == null)
            return null;

        // 댓글 생성 함수에서만 조회하기 때문에 Y 삽입
        oneReply.setUserCheck("Y");

        // firebase로 유저 아이디에 해당하는 기기에 메세지 보내기
        List<String> userIds = replyRepository.getUserIdsAtBoard(replyCreateDTO.getBoardId());

        return oneReply;
    }

    /**
     * 댓글을 작성했다면 true, 아니면 false reurn
     */
    private boolean checkUserMadeReply(int replyId, String userId) {
        return !replyRepository.getReplyCreateByUserId(replyId, userId).isEmpty();

    }

}
