package capstone.sangcom.service.user;

import capstone.sangcom.dto.login.FindPasswordDTO;
import capstone.sangcom.dto.login.LoginDTO;
import capstone.sangcom.dto.login.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;
import capstone.sangcom.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User login(LoginDTO loginDTO) {
        User user = userRepository.findById(loginDTO.getId());
        if (user.equals(loginDTO.getPassword()))
            return user;
        else
            return null;
    }

    @Override
    public String findPassword(FindPasswordDTO findPasswordDTO) {
        return null;
    }

    @Override
    public boolean userCheck(FindPasswordDTO findPasswordDTO) {
        User user = userRepository.findById(findPasswordDTO.getId());
        if (user != null && user.getName().equals(findPasswordDTO.getName()) && user.getSchoolnumber().equals(findPasswordDTO.getStudentid())){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.create(user);
    }

    @Override
    public boolean editProfile(String id) {
        return false;
    }

    @Override
    public boolean editPassword(String id, String newPassword) {
        String encodePassword = passwordEncoder.encode(newPassword);

        return userRepository.update(id, encodePassword);
    }

    @Override
    public boolean editUserInfo(String id, UpdateUserInfoDTO updateUserInfoDTO) {
        return userRepository.update(id, updateUserInfoDTO);
    }

    @Override
    public boolean leave(String id) {
        return userRepository.delete(id);
    }

}
