package capstone.sangcom.repository;

<<<<<<< HEAD
//import capstone.sangcom.dto.boardSection.BoardDTO;
//import capstone.sangcom.dto.boardSection.ReadBoardDTO;
//import capstone.sangcom.dto.boardSection.UpdateBoardDTO;
=======
import capstone.sangcom.dto.boardSection.BoardDTO;
import capstone.sangcom.dto.boardSection.ReadBoardDTO;
import capstone.sangcom.dto.boardSection.UpdateBoardDTO;
>>>>>>> 4decbae6a55ac6b6d4106ded1837d8984be30835
import capstone.sangcom.entity.dao.board.BoardDAO;
import capstone.sangcom.entity.dto.boardSection.BoardDTO;
import capstone.sangcom.entity.dto.boardSection.ReadBoardDTO;
import capstone.sangcom.entity.dto.boardSection.UpdateBoardDTO;
<<<<<<< HEAD
import capstone.sangcom.repository.board.board.MySqlBoardRepository;
//import capstone.sangcom.repository.dao.board.BoardDAO;
=======
import capstone.sangcom.repository.board.MySqlBoardRepository;
>>>>>>> 4decbae6a55ac6b6d4106ded1837d8984be30835
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


    public BoardDAO insert(BoardDAO boardDAO, List<String> paths) {
        return null;
    }

    public List<BoardDTO> findBoard(String type, String title) {
        return null;
    }

    public List<BoardDTO> readAll(String type) {
        return null;
    }

    public ReadBoardDTO readBoard(String userId, String boardId) {
        return null;
    }

    public boolean updateBoard(UpdateBoardDTO updateBoardDTO) {
        return false;
    }

    public boolean deleteBoard(String boardId) {
        return false;
    }

    public boolean isUserWriteBoard(String boardId, String userId) {
        return false;
    }
}
