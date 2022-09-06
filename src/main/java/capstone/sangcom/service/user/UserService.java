package capstone.sangcom.service.user;

import capstone.sangcom.entity.UserDTO;
import capstone.sangcom.entity.dto.userSection.info.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;

public interface UserService {
    public User findById(String id);
    public boolean editPassword(String id, String newPassword);
    public boolean checkPassword(String id, String password);
    public boolean editUserInfo(String id, UpdateUserInfoDTO updateUserInfoDTO);
    public boolean leave(String id);
    public UserDTO getUserInfo(String id);
//    public boolean imageUpload(String id, ImageUploadDTO file);
    public String showImage(String userId);
    public boolean deleteImage(String userId, String path);
}
