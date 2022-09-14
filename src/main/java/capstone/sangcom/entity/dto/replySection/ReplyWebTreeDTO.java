package capstone.sangcom.entity.dto.replySection;

import lombok.Data;

import java.util.List;

@Data
public class ReplyWebTreeDTO {

    private final ReplyWebDTO parent;
    private final List<ReplyWebDTO> child;

}
