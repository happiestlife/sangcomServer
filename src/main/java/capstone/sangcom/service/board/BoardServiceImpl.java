package capstone.sangcom.service.board;

import capstone.sangcom.dto.boardSection.BoardDTO;
import capstone.sangcom.dto.boardSection.BoardDetailDTO;
import capstone.sangcom.dto.boardSection.ReadBoardDTO;
import capstone.sangcom.dto.boardSection.UpdateBoardDTO;
import capstone.sangcom.entity.UserRole;
import capstone.sangcom.repository.board.board.BoardRepository;
import capstone.sangcom.repository.board.boardPath.BoardPathRepository;
import capstone.sangcom.repository.dao.board.BoardDAO;
import capstone.sangcom.repository.dao.board.BoardPathDAO;
import capstone.sangcom.util.board.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{



    private final BoardRepository boardRepository;

    private final BoardPathRepository boardPathRepository;


    @Override
    @Transactional
    public boolean create(BoardDAO boardDAO, List<MultipartFile> images) {
        // 게시글 저장 후 게시글 Id 가져오기
        int boardId = boardRepository.insert(boardDAO);

        // 게시글 이미지에 대한 저장 경로 생성 후 DB에 저장
        List<String> paths = new ArrayList<>();
        for (MultipartFile image : images)
            paths.add(ImageUtils.makePath(ImageUtils.BOARD, image));

        for (String path : paths) {
            if(boardPathRepository.insert(new BoardPathDAO(boardId, path)) == null)
                return false;
        }

        // 서비스 계층에서 이미지 실제 폴더에 저장
        int i = 0;
        for (MultipartFile image : images) {
            try {
                ImageUtils.store(image, new File(paths.get(i++)));
            } catch (IOException e) {
                return false;
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
    @Transactional
    public boolean update(UpdateBoardDTO updateBoardDTO) {
        // 게시글을 작성한 사용자가 맞는지 확인 -> 서비스 계층에서 구현
        if(boardRepository.isUserWriteBoard(updateBoardDTO.getBoardId(), updateBoardDTO.getUserId()))
            return false;

        // 기존의 게시글의 이미지 삭제
        boardPathRepository.delete(updateBoardDTO.getBoardId());

        // 게시글 수정
        if(!boardRepository.updateBoard(updateBoardDTO))
            return false;

        // 게시글 속 새로운 이미지 경로 저장
        List<String> paths = new ArrayList<>();
        for (MultipartFile image : updateBoardDTO.getImages()) {
            String path = ImageUtils.makePath(ImageUtils.BOARD, image);

            paths.add(path);
            boardPathRepository.insert(new BoardPathDAO(updateBoardDTO.getBoardId(), path));
        }

        // 서비스 계층에서 실제 이미지 저장
        int i = 0;
        for (MultipartFile image : updateBoardDTO.getImages()) {
            try {
                ImageUtils.store(image, new File(paths.get(i++)));
            } catch (IOException e) {
                return false;
            }
        }
        return false;
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
            File file = new File(path);
            if(file.exists())
                file.delete();
        }

        return false;
    }
}
