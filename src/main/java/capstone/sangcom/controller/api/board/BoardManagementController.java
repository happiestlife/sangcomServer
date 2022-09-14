package capstone.sangcom.controller.api.board;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.entity.dto.boardSection.UpdateBoardDTO;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardManagementController {

    private final BoardService boardService;

    /**
     * 게시글 생성
     */
    @PostMapping
    public ResponseEntity<SimpleResponse> createBoard(HttpServletRequest request,
                                                      @RequestParam String type,
                                                      UpdateBoardDTO boardData) {
        JwtUser user = (JwtUser) request.getAttribute("user");

        if (boardService.create(user.getId(), type, boardData) != -1)
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SimpleResponse(false));
    }

    /**
     * 게시글 수정
     */
    @PutMapping("/{boardId}")
    public ResponseEntity<SimpleResponse> updateBoard(HttpServletRequest request,
                                                      @PathVariable int boardId,
                                                      UpdateBoardDTO updateData) {
        JwtUser user = (JwtUser) request.getAttribute("user");

        if (boardService.update(user.getId(), boardId, updateData))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SimpleResponse(false));
    }


    /**
     * 게시글 삭제
     */
    @DeleteMapping("/{boardId}")
    public ResponseEntity<SimpleResponse> deleteBoard(HttpServletRequest request,
                                                      @PathVariable int boardId) {
        JwtUser user = (JwtUser) request.getAttribute("user");

        if(boardService.delete(user.getRole(), user.getId(), boardId))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SimpleResponse(false));
    }
}
