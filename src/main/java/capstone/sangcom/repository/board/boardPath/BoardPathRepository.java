package capstone.sangcom.repository.board.boardPath;

import capstone.sangcom.repository.dao.board.BoardPathDAO;

import java.util.List;

public interface BoardPathRepository {
    public BoardPathDAO insert(BoardPathDAO boardPathDAO);
    public List<String> find(int boardId);
    public void delete(int boardId);
}
