package capstone.sangcom.service.board.scrap;

import capstone.sangcom.entity.dto.boardSection.BoardDTO;

import java.util.List;

public interface ScrapService {
    public String scrap(int boardId, String userId);
    public List<BoardDTO> getScrapBoards(String userId);
    public int getScrapCount(int boardId);
}
