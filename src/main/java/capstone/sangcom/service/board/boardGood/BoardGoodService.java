package capstone.sangcom.service.board.boardGood;

public interface BoardGoodService {
    public int getGoodCount(int boardId);
    public String checkGood(int boardId, String userId);
}
