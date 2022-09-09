package capstone.sangcom.repository.user;

import capstone.sangcom.entity.User;
import capstone.sangcom.entity.dao.profile.ImagePathDAO;
import capstone.sangcom.entity.dto.userSection.info.ProfileFileDTO;

import java.util.List;

public interface UserPathRepository {
//    public String imageUpload(ImagePathDAO imagePathDAO);
    public boolean imageUpload(String id, ProfileFileDTO profileFileDTO);
    public boolean findById(String id, String path);
    public List<String> find(String userId);
    public boolean deleteImage(String userId);
    public String showImage(String userId);
    public ImagePathDAO insert(ImagePathDAO imagePathDAO);
}
