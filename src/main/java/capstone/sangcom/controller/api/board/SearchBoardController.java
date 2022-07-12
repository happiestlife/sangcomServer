package capstone.sangcom.controller.api.board;

import capstone.sangcom.controller.api.response.board.BoardResponse;
import capstone.sangcom.dto.boardSection.BoardDTO;
import capstone.sangcom.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class SearchBoardController {

    private final BoardService boardService;

    // 키워드로 게시글 검색
    @GetMapping("/search")
    public ResponseEntity<BoardResponse> searchBoard(@RequestParam String type,
                                                     @RequestParam("title") String keyword) {

        List<BoardDTO> boards = boardService.searchBoards(type, keyword);

        return ResponseEntity
                .ok(new BoardResponse(true, boards));
    }
}
