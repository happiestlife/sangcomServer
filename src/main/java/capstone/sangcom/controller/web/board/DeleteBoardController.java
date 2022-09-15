package capstone.sangcom.controller.web.board;

import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.boardSection.ReadBoardDTO;
import capstone.sangcom.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class DeleteBoardController {

    private final BoardService boardService;

    @GetMapping("/board/{boardId}/delete")
    public String deleteBoard(@PathVariable int boardId,
                              HttpServletRequest request) {
        JwtUser user = (JwtUser) request.getAttribute("user");

        ReadBoardDTO board = boardService.readOneBoard(user.getId(), boardId);

        if (boardService.delete(user.getRole(), user.getId(), boardId)) {
            return "redirect:/board" + "?type=" + board.getBoardDetail().getType();
        } else {
            return "redirect:/board/" + boardId;
        }
    }
}
