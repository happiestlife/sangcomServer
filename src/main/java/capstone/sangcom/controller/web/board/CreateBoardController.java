package capstone.sangcom.controller.web.board;

import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.boardSection.UpdateBoardDTO;
import capstone.sangcom.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board/create")
@RequiredArgsConstructor
public class CreateBoardController {

    private final BoardService boardService;

    @GetMapping
    public String createBoardPage(@RequestParam String type,
                                  Model model) {
        model.addAttribute("type", type);
        model.addAttribute("method", 'c');
        model.addAttribute("name", "작성");

        return "board/boardCreate";
    }

    @PostMapping
    public String createBoard(@RequestParam String type,
                              HttpServletRequest request,
                              UpdateBoardDTO createBoardData) {

        JwtUser user = (JwtUser) request.getAttribute("user");
        List<MultipartFile> images = createBoardData.getImages();

        if(images.size() == 0 || images.get(0).getSize() == 0)
            createBoardData.setImages(null);

        int createBoardId = boardService.create(user.getId(), type, createBoardData);
        if(createBoardId == -1)
            return "redirect:/board/create?type" + type;

        return "redirect:/board/" + createBoardId;
    }

}
