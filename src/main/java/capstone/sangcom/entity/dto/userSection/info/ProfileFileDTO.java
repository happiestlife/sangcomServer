package capstone.sangcom.entity.dto.userSection.info;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProfileFileDTO {
    private final List<MultipartFile> file;
    private final String path;
}


