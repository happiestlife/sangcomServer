package capstone.sangcom.controller.api.reply;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.replySection.CreateAndUpdateReplyDTO;
import capstone.sangcom.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class UpdateReplyController {

    private final ReplyService replyService;


    @PutMapping("/{boardId}/{replyId}")
    public ResponseEntity<SimpleResponse> updateReply(HttpServletRequest request,
                                                      @PathVariable int replyId,
                                                      @RequestBody CreateAndUpdateReplyDTO updateReplyDTO) {
        JwtUser user = (JwtUser) request.getAttribute("user");
        if(replyService.updateReply(updateReplyDTO.getBody(), replyId, user.getId()))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new SimpleResponse(false));
    }
}
