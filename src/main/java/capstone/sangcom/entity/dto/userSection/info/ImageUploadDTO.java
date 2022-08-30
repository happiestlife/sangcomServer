package capstone.sangcom.entity.dto.userSection.info;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ImageUploadDTO {
    private final List<MultipartFile> fileName;
}


