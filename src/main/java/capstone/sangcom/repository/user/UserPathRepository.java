package capstone.sangcom.repository.user;

import capstone.sangcom.entity.User;
import capstone.sangcom.entity.dao.profile.ImagePathDAO;

import java.util.List;

public interface UserPathRepository {
    public ImagePathDAO imageUpload(ImagePathDAO imagePathDAO);
    public boolean findById(String id, String path);
    public List<String> find(String userId);
    public boolean deleteImage(String userId);
    public String showImage(String userId);
}
