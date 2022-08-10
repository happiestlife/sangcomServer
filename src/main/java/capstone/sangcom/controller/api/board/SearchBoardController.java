package capstone.sangcom.controller.api.board;

import capstone.sangcom.controller.api.response.board.BoardResponse;
import capstone.sangcom.entity.dto.boardSection.BoardDTO;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class SearchBoardController {

    private final BoardService boardService;

    /**
     * 키워드로 게시글 조회
     */
    @GetMapping("/search")
    public ResponseEntity<BoardResponse> searchBoard(@RequestParam String type,
                                                     @RequestParam("title") String keyword) {

        List<BoardDTO> boards = boardService.searchBoards(type, keyword);

        return ResponseEntity
                .ok(new BoardResponse(true, boards));
    }

    /**
     * 내가 단 댓글의 게시글 가져오기
     */
    @GetMapping("/myreply")
    public ResponseEntity<BoardResponse> getMyBoard(HttpServletRequest request) {
        JwtUser user = (JwtUser) request.getAttribute("user");
        List<BoardDTO> boards = boardService.readBoardWithMyReply(user.getId());

        return ResponseEntity
                .ok(new BoardResponse(true, boards));
    }
//
//    @GetMapping("/scrap")
//    public ResponseEntity<BoardResponse> getScrapBoard() {
//
//    }
}
