package capstone.sangcom.service.user;

import capstone.sangcom.dto.userSection.info.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;

public interface UserService {

    public User login(LoginDTO loginDTO);
    public String findPassword(FindPasswordDTO findPasswordDTO);
    public boolean userCheck(FindPasswordDTO findPasswordDTO);
    public User findById(String id);
    public boolean editProfile(String id);
    public boolean editPassword(String id, String newPassword);
    public boolean editUserInfo(String id, UpdateUserInfoDTO updateUserInfoDTO);
    public boolean leave(String id);
}
