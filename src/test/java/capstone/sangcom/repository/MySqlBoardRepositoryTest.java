package capstone.sangcom.repository;

import capstone.sangcom.dto.boardSection.BoardDTO;
import capstone.sangcom.dto.boardSection.ReadBoardDTO;
import capstone.sangcom.dto.boardSection.UpdateBoardDTO;
import capstone.sangcom.entity.dao.board.BoardDAO;
import capstone.sangcom.entity.dto.boardSection.BoardDTO;
import capstone.sangcom.entity.dto.boardSection.ReadBoardDTO;
import capstone.sangcom.entity.dto.boardSection.UpdateBoardDTO;
import capstone.sangcom.repository.board.MySqlBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class MySqlBoardRepositoryTest {

    @Autowired
    private MySqlBoardRepository repository;

    @Test
    public void 게시글저장_성공() {

    }


    public BoardDAO insert(BoardDAO boardDAO, List<String> paths);
    public List<BoardDTO> findBoard(String type, String title);
    public List<BoardDTO> readAll(String type);
    public ReadBoardDTO readBoard(String userId, String boardId);
    public boolean updateBoard(UpdateBoardDTO updateBoardDTO);
    public boolean deleteBoard(String boardId);
    public boolean isUserWriteBoard(String boardId, String userId);
}
