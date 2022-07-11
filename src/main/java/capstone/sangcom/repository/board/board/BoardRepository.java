package capstone.sangcom.repository.board.board;

import capstone.sangcom.dto.boardSection.BoardDTO;
import capstone.sangcom.dto.boardSection.BoardDetailDTO;
import capstone.sangcom.dto.boardSection.ReadBoardDTO;
import capstone.sangcom.dto.boardSection.UpdateBoardDTO;
import capstone.sangcom.entity.UserRole;
import capstone.sangcom.repository.dao.board.BoardDAO;

import java.util.List;

public interface BoardRepository {

    public int insert(BoardDAO boardDAO);
    public List<BoardDTO> findBoard(String type, String title);
    public List<BoardDTO> readAll(String type);
    public BoardDetailDTO readBoard(String userId, int boardId);
    public boolean updateBoard(UpdateBoardDTO updateBoardDTO);
    public boolean deleteBoard(int boardId);
    public boolean isUserWriteBoard(int boardId, String userId);

}
