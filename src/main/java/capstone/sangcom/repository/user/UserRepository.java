package capstone.sangcom.repository.user;

import capstone.sangcom.entity.UserRole;
import capstone.sangcom.entity.dto.userSection.info.ImageUploadDTO;
import capstone.sangcom.entity.dto.userSection.info.ProfileDTO;
import capstone.sangcom.entity.dto.userSection.info.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;

import java.util.List;

public interface UserRepository {

    public User insert(User user);
    public User findById(String id);
    public ProfileDTO findById2(String id);
    public List<User> findAll();
    public boolean update();
    public boolean update(String id, String password);
    public String checkPassword(String id, String password);
    public boolean update(String id, UpdateUserInfoDTO updateUserInfoDTO);
    public boolean update(String id, UserRole role);
    public boolean delete(String id);
    public String imageUpload(String id, ImageUploadDTO imageUploadDTO);

}