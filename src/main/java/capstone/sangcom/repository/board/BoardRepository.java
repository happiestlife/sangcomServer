package capstone.sangcom.repository.board;

import capstone.sangcom.dto.boardSection.BoardDTO;
import capstone.sangcom.dto.boardSection.BoardDetailDTO;
import capstone.sangcom.dto.boardSection.ReadBoardDTO;
import capstone.sangcom.entity.UserRole;
import capstone.sangcom.repository.dao.board.BoardDAO;

import java.util.List;

public interface BoardRepository {

    public BoardDAO insert(BoardDAO boardDAO, List<String> paths);
    public BoardDTO findBoard(String type, String title);
    public List<BoardDTO> readAll(String type);
    public ReadBoardDTO readBoard(String userId, String boardId);
    public boolean updateBoard();
    public boolean deleteBoard(UserRole userRole, String boardId, String id);

}
