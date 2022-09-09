package capstone.sangcom.entity.dao.profile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ImagePathDAO {
    private final String userId;
    private final String path;

    public ImagePathDAO(String userId, String path) {
        this.userId = userId;
        this.path = path;
    }
}
