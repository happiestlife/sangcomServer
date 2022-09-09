package capstone.sangcom.controller.web.board;

import capstone.sangcom.entity.dto.boardSection.BoardDTO;
import capstone.sangcom.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class PostingBoardController {

    private final BoardService boardService;

    @GetMapping
    public String getBoards(@RequestParam String type, Model model) {
        List<BoardDTO> boards = boardService.readAll(type);
        ArrayList<BoardDTO> tenBoards = new ArrayList<>();

        int size = 0;
        if(boards.size() > 10)
            size = 10;
        else
            size = boards.size();

        for (int i = 0; i < size; i++) {
            tenBoards.add(boards.get(i));
        }

        model.addAttribute("title", getTitle(type));
        model.addAttribute("boards", tenBoards);
        model.addAttribute("type", type);

        return "board/boards";
    }

    private String getTitle(String type) {
        // 자유 게시판 계열 (free**)
        char c = type.charAt(0);
        StringBuilder sb = new StringBuilder();
        if (c == 'f') {
            char grade = type.charAt(type.length() - 1);
            if(grade == '1'){
                sb.append("1학년 ");
            }else if(grade == '2'){
                sb.append("2학년 ");
            } else if (grade == '3') {
                sb.append("3학년 ");
            }else{
                sb.append("전교생 ");
            }

            sb.append("자유게시판");
        }
        // 학생회 (council)
        else if (c == 'c' && type.charAt(1) == 'o') {
            sb.append("학생회 공지");
        }
        // 학생 건의함 (suggest)
        else if( c == 's'){
            sb.append("학생 건의함");
        }
        // 동아리 (club)
        else if (c == 'c') {
            sb.append("동아리 게시판");
        }

        return sb.toString();
    }
}
