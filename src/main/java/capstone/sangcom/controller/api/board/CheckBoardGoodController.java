package capstone.sangcom.controller.api.board;

import capstone.sangcom.controller.api.response.common.GoodAndScrapCheckResponse;
import capstone.sangcom.controller.api.response.common.GoodAndScrapCountResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.service.board.boardGood.BoardGoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class CheckBoardGoodController {

    private final BoardGoodService boardGoodService;

    /**
     * 게시글 좋아요
     */
    @GetMapping("/good/{boardId}")
    public ResponseEntity<GoodAndScrapCheckResponse> checkBoardGood(
                                                HttpServletRequest request,
                                                @PathVariable int boardId) {
        JwtUser user = (JwtUser) request.getAttribute("user");

        String stat = boardGoodService.checkGood(boardId, user.getId());
        if(stat != null)
            return ResponseEntity
                    .ok(new GoodAndScrapCheckResponse(true, stat));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new GoodAndScrapCheckResponse(false, null));
    }

    /**
     * 게시글 좋아요 개수 조회
     */
    @GetMapping("/goodCount/{boardId}")
    public ResponseEntity<GoodAndScrapCountResponse> getBoardGoodCount(@PathVariable int boardId) {
        int goodCount = boardGoodService.getGoodCount(boardId);

        return ResponseEntity
                .ok(new GoodAndScrapCountResponse(true, goodCount));

        // 익셉션 상황에 따른 실패 ResponseEntity 날리기
    }
}
