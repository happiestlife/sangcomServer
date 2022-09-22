package capstone.sangcom.controller.web.main;

import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.boardSection.BoardDTO;
import capstone.sangcom.entity.dto.todoSection.GetTodolistResponseDTO;
import capstone.sangcom.service.board.BoardService;
import capstone.sangcom.service.todo.TodoService;
import capstone.sangcom.service.user.UserService;
import capstone.sangcom.util.image.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ImageUtils imageUtils;

    private final UserService userService;

    private final TodoService todoService;

    private final BoardService boardService;

    @GetMapping
    public String getMainPage(HttpServletRequest request,
                              @CookieValue String acc,
                              Model model) {
        // user의 개인정보
        JwtUser user = (JwtUser) request.getAttribute("user");

        String profile = userService.showImage(user.getId());
        String profilePath = null;
        if(profile != null)
            profilePath = imageUtils.getPath(profile);
        else
            profilePath = "whiteBackgroundImage.png";

        model.addAttribute("acc", acc);

        model.addAttribute("user", user);

        model.addAttribute("profilePath", profilePath);

        // user의 to-do 리스트
        Calendar c = Calendar.getInstance();
        List<GetTodolistResponseDTO> todolist =
                todoService.getTodolist(user.getId(), c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DATE));

        model.addAttribute("todoList", todolist);

        // 자유게시글과 학생회 공지글
        List<BoardDTO> free = boardService.readAll("free");
        List<BoardDTO> council = boardService.readAll("council");

        ArrayList<BoardDTO> freePosts = new ArrayList<>();
        ArrayList<BoardDTO> councilPosts = new ArrayList<>();
        for (int i = 0; i < free.size(); i++) {
            freePosts.add(free.get(i));
            if(i == 4)
                break;
        }
        for (int i = 0; i < council.size(); i++) {
            councilPosts.add(council.get(i));
            if(i == 4)
                break;
        }

        model.addAttribute("freePosts", freePosts);

        model.addAttribute("councilPosts", councilPosts);

        return "main";
    }
}
