package capstone.sangcom.repository.dao.replyReport;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
public class ReplyReportDAO {

    private final int report_id;
    private final int board_id;
    private final int reply_id;
    private final String send_id;
    private final String revc_id;
    private final String body;
    private final String regdate;

    public ReplyReportDAO(int report_id, int board_id, int reply_id, String send_id, String revc_id, String body) {
        this.report_id = report_id;
        this.board_id = board_id;
        this.reply_id = reply_id;
        this.send_id = send_id;
        this.revc_id = revc_id;
        this.body = body;
        this.regdate = recordTime();
    }

    private final String recordTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return dateFormat.format(now);
    }
}
