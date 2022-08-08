package capstone.sangcom.entity.dto.mailSection;

import lombok.Data;

@Data
public class MailInfoDTO {

    private final String toAddress;
    private final String subject;
    private final String message;

}
