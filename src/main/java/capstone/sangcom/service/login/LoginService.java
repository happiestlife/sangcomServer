package capstone.sangcom.service.login;

import capstone.sangcom.controller.api.response.login.LoginResponse;
import capstone.sangcom.entity.dto.loginSection.find.FindPasswordDTO;
import capstone.sangcom.entity.dto.loginSection.login.LoginDTO;
import capstone.sangcom.entity.User;

/**
 * 로그인 페이지에서 실행 가능한
 * 로그인, 회원가입, 비밀번호 찾기 기능이 존재
 */
public interface LoginService {

    public LoginResponse login(LoginDTO loginDTO);
    public String findPassword(FindPasswordDTO findPasswordDTO);
    public User register(User user);

}
