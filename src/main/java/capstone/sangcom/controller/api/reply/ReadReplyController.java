package capstone.sangcom.controller.api.reply;

import capstone.sangcom.controller.api.response.reply.ReadReplyResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.replySection.ReplyTreeDTO;
import capstone.sangcom.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReadReplyController {

    private final ReplyService replyService;

    /**
     * 댓글 조회
     */
    @GetMapping("/{boardId}")
    public ResponseEntity<ReadReplyResponse> readAllReply(HttpServletRequest request,
                                                          @PathVariable int boardId) {
        JwtUser user = (JwtUser) request.getAttribute("user");
        List<ReplyTreeDTO> allReplies = replyService.readReply(user.getId(), boardId);

        return ResponseEntity
                .ok(new ReadReplyResponse(true, allReplies));
        // 실패 조건 추가하기
    }

}
