package capstone.sangcom.service.user;

import capstone.sangcom.entity.dao.profile.ImagePathDAO;
import capstone.sangcom.entity.dto.userSection.info.ProfileDTO;
import capstone.sangcom.entity.dto.userSection.info.ProfileFileDTO;
import capstone.sangcom.entity.dto.userSection.info.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;
import capstone.sangcom.repository.user.UserPathRepository;
import capstone.sangcom.repository.user.UserRepository;
import capstone.sangcom.util.user.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final UserPathRepository userPathRepository;

    private final PasswordEncoder passwordEncoder;

    private final ImageUtils imageUtils;

    @Override
    @Transactional
    public User findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public boolean editPassword(String id, String newPassword) {
        String encodePassword = passwordEncoder.encode(newPassword);

        return userRepository.update(id, encodePassword);
    }

    @Override
    @Transactional
    public boolean checkPassword(String id, String password) {
        String newPassword = userRepository.checkPassword(id, password);
        System.out.println(newPassword);
        return passwordEncoder.matches(password, newPassword);
    }

    @Override
    @Transactional
    public boolean editUserInfo(String id, UpdateUserInfoDTO updateUserInfoDTO) {
        return userRepository.update(id, updateUserInfoDTO);
    }

    @Override
    public boolean leave(String id) {
        return userRepository.delete(id);
    }

    @Override
    public ProfileDTO getUserInfo(String id) {
        ProfileDTO user = userRepository.findById2(id);
        return user;
    }
    @Override
    @Transactional
    public boolean imageUpload(String id, ProfileFileDTO file) {

        List<String> paths = userPathRepository.find(id);

        if(paths.size() == 1){
            deleteImage(id, paths.get(0));
            imageUtils.delete(paths.get(0));
        }
        String path = imageUtils.makePath(ImageUtils.PROFILE, file.getProfile());
        if(userPathRepository.insert(new ImagePathDAO(id, path)) == null){
            return false;
        }

        // 실제 이미지 저장하기
        try {
            imageUtils.store(file.getProfile(), new File(path));
        }catch(IOException e){
            return false;
        }
        return true;
    }


    @Override
    @Transactional
    public String showImage(String userId) {
        return userPathRepository.showImage(userId);
    }

    @Override
    @Transactional
    public boolean deleteImage(String userId, String path) {
        if(userPathRepository.findById(userId, path))
            return false;

        List<String> paths = userPathRepository.find(userId);

        if(!userPathRepository.deleteImage(userId))
            return false;


        for(String path1: paths){
            imageUtils.delete(path1);
        }

        return true;
    }
}
