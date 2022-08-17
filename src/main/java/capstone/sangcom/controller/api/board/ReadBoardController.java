package capstone.sangcom.controller.api.board;

import capstone.sangcom.controller.api.response.board.BoardDetailResponse;
import capstone.sangcom.controller.api.response.board.BoardResponse;
import capstone.sangcom.entity.dto.boardSection.BoardDTO;
import capstone.sangcom.entity.dto.boardSection.BoardDetailDTO;
import capstone.sangcom.entity.dto.boardSection.ReadBoardDTO;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class ReadBoardController {

    private final BoardService boardService;

    /**
     * 입력한 타입에 해당하는 게시글 모두 조회
     */
    @GetMapping
    public ResponseEntity<BoardResponse> readAllTheSameTypeOfBoard(@RequestParam String type) {
        List<BoardDTO> boards = boardService.readAll(type);

        return ResponseEntity
                .ok(new BoardResponse(true, boards));
    }

    /**
     * 게시글 아이디에 해당하는 게시글 가져오기
     */
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDetailResponse> readOneBoard(HttpServletRequest request,
                                                            @PathVariable int boardId) {
        JwtUser user = (JwtUser) request.getAttribute("user");
        ReadBoardDTO boardData = boardService.readOneBoard(user.getId(), boardId);

        ArrayList<BoardDetailDTO> board = new ArrayList<>();
        board.add(boardData.getBoardDetail());

        return ResponseEntity
                .ok(new BoardDetailResponse(true, board, boardData.getPaths()));
    }
}
