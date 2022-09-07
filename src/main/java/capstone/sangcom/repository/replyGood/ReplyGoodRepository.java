package capstone.sangcom.repository.replyGood;

public interface ReplyGoodRepository {
    public boolean insert(int replyId, String userId);
    public int getReplyGoodId(int replyId, String userId);
    public int getGoodCount(int replyId);
    public boolean delete(int goodId);
}
