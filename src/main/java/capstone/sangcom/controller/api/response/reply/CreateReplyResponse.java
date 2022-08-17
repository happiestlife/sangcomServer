package capstone.sangcom.controller.api.response.reply;

import capstone.sangcom.entity.dto.replySection.ReplyDTO;
import capstone.sangcom.entity.dto.replySection.ReplyTreeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class CreateReplyResponse {

    private final boolean success;
    private final ReplyDTO data;

}
