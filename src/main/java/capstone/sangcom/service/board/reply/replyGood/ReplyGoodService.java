package capstone.sangcom.service.board.reply.replyGood;

public interface ReplyGoodService {
    public String checkGood(int replyId, String userId);
    public int getGoodCount(int replyId);
}
