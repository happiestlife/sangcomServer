package capstone.sangcom.controller.api.reply;

import capstone.sangcom.controller.api.response.reply.CreateReplyResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.replySection.CreateAndUpdateReplyDTO;
import capstone.sangcom.entity.dto.replySection.ReplyCreateDTO;
import capstone.sangcom.entity.dto.replySection.ReplyDTO;
import capstone.sangcom.service.board.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class CreateReplyController {

    private final ReplyService replyService;

    /**
     * 댓글 작성
     */
    @PostMapping("/{boardId}")
    public ResponseEntity<CreateReplyResponse> createReplyAyBoard(HttpServletRequest request,
                                                                  @PathVariable int boardId,
                                                                  @RequestBody CreateAndUpdateReplyDTO createReplyDTO) {
        JwtUser user = (JwtUser) request.getAttribute("user");
        ReplyDTO createReply = replyService.createReplyAtBoard(new ReplyCreateDTO(createReplyDTO.getBody(), user.getId(), boardId, 0, 0));
        if (createReply != null)
            return ResponseEntity
                    .ok(new CreateReplyResponse(true, createReply));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new CreateReplyResponse(false, null));
    }

    /**
     * 댓글에 대한 댓글 작성
     */
    @PostMapping("/{boardId}/{replyId}")
    public ResponseEntity<CreateReplyResponse> createReplyAtReply(HttpServletRequest request,
                                                                  @PathVariable int boardId,
                                                                  @PathVariable int replyId,
                                                                  @RequestBody CreateAndUpdateReplyDTO createReplyDTO) {
        JwtUser user = (JwtUser) request.getAttribute("user");
        ReplyDTO createReply = replyService.createReplyAtReply(new ReplyCreateDTO(createReplyDTO.getBody(), user.getId(), boardId, replyId, 1));
        if(createReply != null)
            return ResponseEntity
                    .ok(new CreateReplyResponse(true, createReply));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new CreateReplyResponse(false, null));
    }
}
