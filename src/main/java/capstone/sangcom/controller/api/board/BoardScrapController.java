package capstone.sangcom.controller.api.board;

import capstone.sangcom.controller.api.response.board.BoardResponse;
import capstone.sangcom.controller.api.response.common.GoodAndScrapCheckResponse;
import capstone.sangcom.controller.api.response.common.GoodAndScrapCountResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.boardSection.BoardDTO;
import capstone.sangcom.service.board.scrap.ScrapService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardScrapController {

    private final ScrapService scrapService;

    /**
     * 스크립 게시글 조회
     */
    @GetMapping("/scrap")
    public ResponseEntity<BoardResponse> getScrapBoard(HttpServletRequest request) {
        JwtUser user = getUserInfo(request);

        List<BoardDTO> scrapBoards = scrapService.getScrapBoards(user.getId());
        return ResponseEntity
                .ok(new BoardResponse(true, scrapBoards));
        // 오류 상황 추가
    }

    /**
     * 게시글 스크랩
     */
    @GetMapping("/scrap/{boardId}")
    public ResponseEntity<GoodAndScrapCheckResponse> scrapBoard(HttpServletRequest request,
                                                                @PathVariable int boardId) {
        JwtUser user = getUserInfo(request);

        String stat = scrapService.scrap(boardId, user.getId());
        if(stat != null)
            return ResponseEntity
                    .ok(new GoodAndScrapCheckResponse(true, stat));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new GoodAndScrapCheckResponse(false, null));
    }

    /**
     * 게시글 스크랩 개수 조회
     */
    @GetMapping("/scrapcount/{boardId}")
    public ResponseEntity<GoodAndScrapCountResponse> getScrapCount(@PathVariable int boardId) {
        int scrapCount = scrapService.getScrapCount(boardId);
        if(scrapCount != -1)
            return ResponseEntity
                    .ok(new GoodAndScrapCountResponse(true, scrapCount));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new GoodAndScrapCountResponse(false, -1));
    }

    private JwtUser getUserInfo(HttpServletRequest request) {
        return (JwtUser) request.getAttribute("user");
    }
}
