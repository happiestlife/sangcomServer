package capstone.sangcom.repository.scrap;

import capstone.sangcom.entity.dto.boardSection.BoardDTO;

import java.util.List;

public interface ScrapRepository {
    public boolean insert(int boardId, String userId);

    public int getScrapId(int boardId, String userId);
    public List<BoardDTO> getScrapBoardData(String userId);
    public int getScrapCount(int boardId);
    public boolean delete(int scrapId);
}
