package capstone.sangcom.controller.web.board;

import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.boardSection.ReadBoardDTO;
import capstone.sangcom.entity.dto.replySection.ReplyDTO;
import capstone.sangcom.entity.dto.replySection.ReplyTreeDTO;
import capstone.sangcom.entity.dto.replySection.ReplyWebDTO;
import capstone.sangcom.entity.dto.replySection.ReplyWebTreeDTO;
import capstone.sangcom.entity.dto.userSection.info.ProfileFileDTO;
import capstone.sangcom.service.board.BoardService;
import capstone.sangcom.service.board.reply.ReplyService;
import capstone.sangcom.service.user.UserService;
import capstone.sangcom.util.image.ImageUtils;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class ReadPostController {

    private final ImageUtils imageUtils;

    private final UserService userService;

    private final BoardService boardService;

    private final ReplyService replyService;

    @GetMapping("/{boardId}")
    public String getOneBoard(@PathVariable int boardId,
                              HttpServletRequest request,
                              Model model) {
        JwtUser user = (JwtUser) request.getAttribute("user");

        // 게시판 정보 가져오기
        ReadBoardDTO board = boardService.readOneBoard(user.getId(), boardId);

        boolean isWriter = false;
        if(user.getId().equals(board.getBoardDetail().getUser_id()))
            isWriter = true;

        // 게시글에 삽입된 이미지 파일의 이름 가져오기
        ArrayList<String> imgPaths = new ArrayList<>();
        for (String path : board.getPaths()) {
            imgPaths.add(imageUtils.getPath(path));
        }

        // 게시글 작성 유저의 프로필 가져오기
        String writerImageName = imageUtils.getPath(userService.showImage(board.getBoardDetail().getUser_id()));

        // 해당 게시판에 달린 댓글 정보 가져오기
        List<ReplyWebTreeDTO> replies = makeWebReplyForm(replyService.readReply(user.getId(), boardId));

        model.addAttribute("board", board.getBoardDetail());
        model.addAttribute("imagePath", imgPaths);
        model.addAttribute("writerImagePath", writerImageName);
        model.addAttribute("isWriter", isWriter);

        model.addAttribute("replies", replies);

        return "board/boardContent";
    }

    private List<ReplyWebTreeDTO> makeWebReplyForm(List<ReplyTreeDTO> apiReplies) {
        List<ReplyWebTreeDTO> replies = new ArrayList<>();

        for (ReplyTreeDTO replyTree : apiReplies) {
            // parent 복제
            ReplyWebDTO parentReply = new ReplyWebDTO(replyTree.getParent());

            String profilePath = userService.showImage(replyTree.getParent().getUserId());
            parentReply.setProfilePath(imageUtils.getPath(profilePath));

            // child 복제
            ArrayList<ReplyWebDTO> childReplies = new ArrayList<>();
            for (ReplyDTO replyDTO : replyTree.getChild()) {
                ReplyWebDTO childReply = new ReplyWebDTO(replyDTO);

                profilePath = userService.showImage(replyTree.getParent().getUserId());
                childReply.setProfilePath(imageUtils.getPath(profilePath));

                childReplies.add(childReply);
            }

            replies.add(new ReplyWebTreeDTO(parentReply, childReplies));
        }

        return replies;
    }
}
