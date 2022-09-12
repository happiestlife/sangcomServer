package capstone.sangcom.service.board;

import capstone.sangcom.entity.dto.boardSection.BoardDTO;
import capstone.sangcom.entity.dto.boardSection.BoardDetailDTO;
import capstone.sangcom.entity.dto.boardSection.ReadBoardDTO;
import capstone.sangcom.entity.dto.boardSection.UpdateBoardDTO;
import capstone.sangcom.entity.UserRole;
import capstone.sangcom.repository.board.BoardRepository;
import capstone.sangcom.repository.boardPath.BoardPathRepository;
import capstone.sangcom.entity.dao.board.BoardDAO;
import capstone.sangcom.entity.dao.board.BoardPathDAO;
import capstone.sangcom.util.image.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final ImageUtils imageUtils;

    private final BoardRepository boardRepository;

    private final BoardPathRepository boardPathRepository;

    @Override
    @Transactional
    public boolean create(String userId, String type, UpdateBoardDTO boardData) {
        // 게시글 저장 후 게시글 Id 가져오기
        int boardId = boardRepository.insert(new BoardDAO(
                -1, boardData.getTitle(), boardData.getBody(),
                userId, type));

        // 게시글 이미지에 대한 저장 경로 생성 후 DB에 저장
        if(boardData.getImages() != null) {
            List<String> paths = new ArrayList<>();
            for (MultipartFile image : boardData.getImages()) {
                paths.add(imageUtils.makePath(ImageUtils.BOARD, image));
            }
            for (String path : paths) {
                if (boardPathRepository.insert(new BoardPathDAO(boardId, path)) == null)
                    return false;
            }

            // 서비스 계층에서 이미지 실제 폴더에 저장
            int i = 0;
            for (MultipartFile image : boardData.getImages()) {
                try {
                    imageUtils.store(image, new File(paths.get(i++)));
                } catch (IOException e) {
                    return false;
                }
            }
        }

        // 게시글 type이 notice인 경우 전체 알림 메세지 전송

        return true;
    }

    @Override
    @Transactional
    public List<BoardDTO> searchBoards(String type, String keyword) {
        return boardRepository.findBoard(type, keyword);
    }

    @Override
    @Transactional
    public List<BoardDTO> readAll(String type) {
        return boardRepository.readAll(type);
    }

    @Override
    @Transactional
    public ReadBoardDTO readOneBoard(String userId, int boardId) {
        // 하나의 게시글 정보 조회
        BoardDetailDTO boardDetailDTO = boardRepository.readBoard(userId, boardId);

        // 해당 게시글의 이미지 경로 조회
        List<String> paths = boardPathRepository.find(boardId);

        return new ReadBoardDTO(boardDetailDTO, paths);
    }

    @Override
    public List<BoardDTO> readBoardWithMyReply(String id) {
        return boardRepository.readBoardWithReply(id);
    }

    @Override
    @Transactional
    public boolean update(String userId, int boardId, UpdateBoardDTO updateBoardDTO) {
        // 게시글을 작성한 사용자가 맞는지 확인 -> 서비스 계층에서 구현
        if(boardRepository.isUserWriteBoard(boardId, userId))
            return false;

        // 기존의 게시글의 이미지 삭제
        List<String> paths = boardPathRepository.find(boardId);
        boardPathRepository.delete(boardId);
        for (String imagePath : paths) {
            imageUtils.delete(imagePath);
        }

        // 게시글 수정
        if(!boardRepository.updateBoard(boardId, updateBoardDTO))
            return false;

        // 게시글 속 새로운 이미지 경로 저장
        if(updateBoardDTO.getImages() != null) {
            paths = new ArrayList<>();
            for (MultipartFile image : updateBoardDTO.getImages()) {
                String path = imageUtils.makePath(ImageUtils.BOARD, image);

                paths.add(path);
                if (boardPathRepository.insert(new BoardPathDAO(boardId, path)) == null)
                    return false;
            }

            // 서비스 계층에서 실제 이미지 저장
            int i = 0;
            for (MultipartFile image : updateBoardDTO.getImages()) {
                try {
                    imageUtils.store(image, new File(paths.get(i++)));
                } catch (IOException e) {
                    System.out.println("IMAGE INSERT EXCEPTION IS OCCURRED");
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    @Transactional
    public boolean delete(UserRole role, String userId, int boardId) {
        // 게시글을 작성한 사용자가 맞는지 확인 -> 서비스 계층에서 구현
        if(boardRepository.isUserWriteBoard(boardId, userId))
            return false;

        // 게시글 삭제 전 게시글의 이미지 경로 가져오기
        List<String> paths = boardPathRepository.find(boardId);

        // 게시글 삭제
        if(!boardRepository.deleteBoard(boardId))
            return false;

        // 이미지 삭제 -> 서비스 계층에서 구현
        for (String path : paths) {
            imageUtils.delete(path);
        }

        return true;
    }
}
