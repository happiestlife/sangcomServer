package capstone.sangcom.controller.web.main;

import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.todoSection.GetTodolistResponseDTO;
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
import java.util.Calendar;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ImageUtils imageUtils;

    private final UserService userService;

    private final TodoService todoService;

    @GetMapping
    public String getMainPage(HttpServletRequest request,
                              @CookieValue String acc,
                              Model model) {
        JwtUser user = (JwtUser) request.getAttribute("user");

        String profilePath = imageUtils.getPath(userService.showImage(user.getId()));

        model.addAttribute("acc", acc);

        model.addAttribute("user", user);

        model.addAttribute("profilePath", profilePath);

        Calendar c = Calendar.getInstance();
        List<GetTodolistResponseDTO> todolist =
                todoService.getTodolist(user.getId(), c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DATE));

        model.addAttribute("todoList", todolist);

        return "main";
    }
}
