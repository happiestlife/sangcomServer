package capstone.sangcom.service.board.boardGood;

import capstone.sangcom.repository.boardgood.BoardGoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardGoodServiceImpl implements BoardGoodService{

    private final BoardGoodRepository boardGoodRepository;

    @Override
    public int getGoodCount(int boardId) {
        return boardGoodRepository.countBoardGood(boardId);
    }

    @Override
    public String checkGood(int boardId, String userId) {
        int goodId = boardGoodRepository.getBoardGoodCheckedByUser(boardId, userId);
        if (goodId == -1) {
            if(boardGoodRepository.insert(boardId, userId) == false)
                return null;

            return "INSERT";
        } else {
            if(boardGoodRepository.delete(goodId) == false)
                return null;

            return "DELETE";
        }


    }
}
