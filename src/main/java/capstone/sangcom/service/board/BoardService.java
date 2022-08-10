package capstone.sangcom.service.board;

import capstone.sangcom.entity.dto.boardSection.BoardDTO;
import capstone.sangcom.entity.dto.boardSection.ReadBoardDTO;
import capstone.sangcom.entity.dto.boardSection.UpdateBoardDTO;
import capstone.sangcom.entity.UserRole;

import java.util.List;

public interface BoardService {
    public boolean create(String userId, String type, UpdateBoardDTO boardData);

    public List<BoardDTO> searchBoards(String type, String keyword);

    public List<BoardDTO> readAll(String type);

    public ReadBoardDTO readOneBoard(String userId, int boardId);

    public List<BoardDTO> readBoardWithMyReply(String id);

    public boolean update(String userId, int boardId, UpdateBoardDTO updateBoardDTO);

    public boolean delete(UserRole role, String userId, int boardId);

}
