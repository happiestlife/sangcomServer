package capstone.sangcom.entity.dao.replyReport;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
public class ReportBoardDAO {

    private final int report_id;
    private final int board_id;
    private final String send_id;
    private final String revc_id;
    private final String body;
    private final String regdate;

    public ReportBoardDAO(int report_id, int board_id, String send_id, String revc_id, String body) {
        this.report_id = report_id;
        this.board_id = board_id;
        this.send_id = send_id;
        this.revc_id = revc_id;
        this.regdate = recordTime();
        this.body = body;
    }

    private final String recordTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return dateFormat.format(now);
    }
}
