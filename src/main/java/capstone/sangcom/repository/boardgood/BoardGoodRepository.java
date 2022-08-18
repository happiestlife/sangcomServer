package capstone.sangcom.repository.boardGood;

public interface BoardGoodRepository {
    public boolean insert(int boardId, String userId);
    public int getBoardGoodId(int boardId, String userId);
    public int countBoardGood(int boardId);
    public boolean delete(int goodId);

}
