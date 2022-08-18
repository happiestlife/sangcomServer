package capstone.sangcom.service.board.scrap;

import capstone.sangcom.entity.dto.boardSection.BoardDTO;
import capstone.sangcom.repository.scrap.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrapServiceImpl implements ScrapService{

    private final ScrapRepository scrapRepository;

    @Override
    public String scrap(int boardId, String userId) {
        int scrapId = scrapRepository.getScrapId(boardId, userId);
        if (scrapId == -1) {
            if(scrapRepository.insert(boardId, userId) == false)
                return null;

            return "INSERT";
        } else {
            if(scrapRepository.delete(scrapId) == false)
                return null;

            return "DELETE";
        }
    }

    @Override
    public List<BoardDTO> getScrapBoards(String userId) {
        return scrapRepository.getScrapBoardData(userId);
    }

    @Override
    public int getScrapCount(int boardId) {
        return scrapRepository.getScrapCount(boardId);
    }
}
