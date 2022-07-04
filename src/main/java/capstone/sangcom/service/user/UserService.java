package capstone.sangcom.service.user;

import capstone.sangcom.controller.api.response.login.LoginResponse;
import capstone.sangcom.dto.loginSection.find.FindPasswordDTO;
import capstone.sangcom.dto.loginSection.login.LoginDTO;
import capstone.sangcom.dto.userSection.info.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;


public interface UserService {

    public LoginResponse login(LoginDTO loginDTO);
    public String findPassword(FindPasswordDTO findPasswordDTO);
    public User findById(String id);
    public User register(User use);
    public boolean editProfile(String id);
    public boolean editPassword(String id, String newPassword);
    public boolean editUserInfo(String id, UpdateUserInfoDTO updateUserInfoDTO);
    public boolean leave(String id);

}
