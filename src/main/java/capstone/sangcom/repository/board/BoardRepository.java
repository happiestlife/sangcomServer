package capstone.sangcom.repository.board;

import capstone.sangcom.entity.dto.boardSection.BoardDTO;
import capstone.sangcom.entity.dto.boardSection.BoardDetailDTO;
import capstone.sangcom.entity.dto.boardSection.UpdateBoardDTO;
import capstone.sangcom.entity.dao.board.BoardDAO;

import java.util.List;

public interface BoardRepository {

    public int insert(BoardDAO boardDAO);
    public List<BoardDTO> findBoard(String type, String title);
    public List<String> findUser(String boardId);
    public List<BoardDTO> readAll(String type);
    public BoardDetailDTO readBoard(String userId, int boardId);
    public List<BoardDTO> readBoardWithReply(String userId);
    public boolean updateBoard(int boardId, UpdateBoardDTO updateBoardDTO);
    public boolean deleteBoard(int boardId);
    public boolean isUserWriteBoard(int boardId, String userId);

}
