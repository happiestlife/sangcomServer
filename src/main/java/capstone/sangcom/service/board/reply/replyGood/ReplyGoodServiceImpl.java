package capstone.sangcom.service.board.reply.replyGood;

import capstone.sangcom.repository.replyGood.ReplyGoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyGoodServiceImpl implements ReplyGoodService{

    private final ReplyGoodRepository replyGoodRepository;

    @Override
    public String checkGood(int replyId, String userId) {
        int replyGoodId = replyGoodRepository.getReplyGoodId(replyId, userId);
        // 댓글에 좋아요를 누르지 않은 사용자라면 좋아요
        if (replyGoodId == -1) {
            if(replyGoodRepository.insert(replyId, userId) == false)
                return null;

            return "INSERT";
        }
        // 반대의 경우, 댓글 좋아요 해제
        else {
            if(replyGoodRepository.delete(replyGoodId) == false)
                return null;

            return "DELETE";
        }
    }

    @Override
    public int getGoodCount(int replyId) {
        return replyGoodRepository.getGoodCount(replyId);
    }
}
