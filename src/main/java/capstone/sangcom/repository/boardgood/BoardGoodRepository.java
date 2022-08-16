package capstone.sangcom.repository.boardgood;

public interface BoardGoodRepository {
    public boolean insert(int boardId, String userId);
    public int getBoardGoodCheckedByUser(int boardId, String userId);
    public int countBoardGood(int boardId);
    public boolean delete(int boardId);

}
