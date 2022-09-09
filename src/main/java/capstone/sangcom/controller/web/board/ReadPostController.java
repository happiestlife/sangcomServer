package capstone.sangcom.controller.web.board;

import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.boardSection.ReadBoardDTO;
import capstone.sangcom.entity.dto.replySection.ReplyTreeDTO;
import capstone.sangcom.service.board.BoardService;
import capstone.sangcom.service.board.reply.ReplyService;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class ReadPostController {

    private final BoardService boardService;

    private final ReplyService replyService;

    @GetMapping("/{boardId}")
    public String getOneBoard(@PathVariable int boardId,
                              HttpServletRequest request,
                              Model model) {
        JwtUser user = (JwtUser) request.getAttribute("user");

        // 게시판 정보 가져오기
        ReadBoardDTO board = boardService.readOneBoard(user.getId(), boardId);

        // 해당 게시판에 달린 댓글 정보 가져오기
        List<ReplyTreeDTO> replies = replyService.readReply(user.getId(), boardId);

        model.addAttribute("board", board.getBoardDetail());
        model.addAttribute("imagePath", board.getPaths());

        model.addAttribute("replies", replies);

        return "board/boardContent";
    }
}
