package capstone.sangcom.repository.boardPath;

import capstone.sangcom.entity.dao.board.BoardPathDAO;

import java.util.List;

public interface BoardPathRepository {
    public BoardPathDAO insert(BoardPathDAO boardPathDAO);
    public List<String> find(int boardId);
    public void delete(int boardId);
}
