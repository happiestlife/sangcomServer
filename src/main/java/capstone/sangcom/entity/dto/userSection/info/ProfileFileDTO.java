package capstone.sangcom.entity.dto.userSection.info;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfileFileDTO {
    private final MultipartFile profile;
}


