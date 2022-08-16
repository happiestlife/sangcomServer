package capstone.sangcom.controller.api.response.reply;

import capstone.sangcom.entity.dto.replySection.ReplyTreeDTO;
import lombok.Data;

import java.util.List;

@Data
public class ReadReplyResponse {
    private final boolean success;
    private final List<ReplyTreeDTO> data;
}
