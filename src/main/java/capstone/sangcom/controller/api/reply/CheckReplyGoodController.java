package capstone.sangcom.controller.api.reply;

import capstone.sangcom.controller.api.response.common.GoodAndScrapCheckResponse;
import capstone.sangcom.controller.api.response.common.GoodAndScrapCountResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.service.board.reply.replyGood.ReplyGoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class CheckReplyGoodController {

    private final ReplyGoodService replyGoodService;

    @GetMapping("/good/{replyId}")
    public ResponseEntity<GoodAndScrapCheckResponse> checkGood(HttpServletRequest request,
                                                               @PathVariable int replyId) {
        JwtUser user = (JwtUser) request.getAttribute("user");
        String stat = replyGoodService.checkGood(replyId, user.getId());
        if (stat != null) {
            return ResponseEntity
                    .ok(new GoodAndScrapCheckResponse(true, stat));
        } else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new GoodAndScrapCheckResponse(false, null));
    }

    @GetMapping("/goodcount/{replyId}")
    public ResponseEntity<GoodAndScrapCountResponse> getReplyGoodCount(@PathVariable int replyId) {
        int goodCount = replyGoodService.getGoodCount(replyId);
        if(goodCount != -1)
            return ResponseEntity
                    .ok(new GoodAndScrapCountResponse(true, goodCount));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new GoodAndScrapCountResponse(false, -1));

    }
}
