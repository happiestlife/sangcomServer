package capstone.sangcom.service.user;

import capstone.sangcom.controller.api.response.login.LoginResponse;
import capstone.sangcom.controller.api.response.login.TokenResponse;
import capstone.sangcom.dto.login.FindPasswordDTO;
import capstone.sangcom.dto.login.LoginDTO;
import capstone.sangcom.dto.login.UpdateUserInfoDTO;
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
