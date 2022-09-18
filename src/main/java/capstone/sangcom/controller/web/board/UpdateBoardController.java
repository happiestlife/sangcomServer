package capstone.sangcom.controller.web.board;

import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.boardSection.ReadBoardDTO;
import capstone.sangcom.entity.dto.boardSection.UpdateBoardDTO;
import capstone.sangcom.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/{boardId}/update")
public class UpdateBoardController {

    private final BoardService boardService;

    @GetMapping
    public String updateBoardPage(@PathVariable int boardId,
                                  HttpServletRequest request,
                                  Model model) {
        JwtUser user = (JwtUser) request.getAttribute("user");

        ReadBoardDTO boardData = boardService.readOneBoard(user.getId(), boardId);

        model.addAttribute("board", boardData.getBoardDetail());
        model.addAttribute("method", 'u');
        model.addAttribute("type", boardData.getBoardDetail().getType());
        model.addAttribute("name", "수정");

        return "board/boardCreate";
    }

    @PostMapping
    public String updateBoard(@PathVariable int boardId,
                              HttpServletRequest request,
                              UpdateBoardDTO updateBoardDTO) {
        JwtUser user = (JwtUser) request.getAttribute("user");

        List<MultipartFile> images = updateBoardDTO.getImages();

        if (images.size() == 0 || images.get(0).getSize() == 0)
            updateBoardDTO.setImages(null);

        if (boardService.update(user.getId(), boardId, updateBoardDTO)) {
            return "redirect:/board/" + boardId;
        } else {
            return "redirect:/board/" + boardId + "/update";
        }
    }
}
