package capstone.sangcom.controller.api.reply;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class DeleteReplyController {

    private final ReplyService replyService;

    @DeleteMapping("/{boardId}/{replyId}")
    public ResponseEntity<SimpleResponse> deleteReply(HttpServletRequest request,
                                                      @PathVariable int replyId) {
        JwtUser user = (JwtUser) request.getAttribute("user");
        if(replyService.deleteReply(user.getRole(), user.getId(), replyId))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new SimpleResponse(false));
    }
}
