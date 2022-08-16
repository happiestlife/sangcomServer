package capstone.sangcom.entity.dto.replySection;

import lombok.Data;

import java.util.List;

@Data
public class ReplyTreeDTO {

    private final ReplyDTO parent;
    private final List<ReplyDTO> child;

}
