package capstone.sangcom.entity.dao.board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
public class BoardDAO {

    private final int board_id;
    private final String title;
    private final String body;
    private final String user_id;
    private final String regdate;
    private final String type;

    public BoardDAO(int board_id, String title, String body, String user_id, String type) {
        this.board_id = board_id;
        this.title = title;
        this.body = body;
        this.user_id = user_id;
        this.regdate = recordTime();
        this.type = type;
    }

    private final String recordTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return dateFormat.format(now);
    }
}
