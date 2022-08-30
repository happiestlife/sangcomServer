package capstone.sangcom.service.user;

import capstone.sangcom.entity.UserDTO;
import capstone.sangcom.entity.dao.profile.ImagePathDAO;
import capstone.sangcom.entity.dto.userSection.info.ImageUploadDTO;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    public User findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDTO getUserInfo(String id) {
        User user = userRepository.findById(id);
        return new UserDTO(user);
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
    @Transactional
    public boolean editUserInfo(String id, UpdateUserInfoDTO updateUserInfoDTO) {
        return userRepository.update(id, updateUserInfoDTO);
    }

    @Override
    public boolean leave(String id) {
        return userRepository.delete(id);
    }

//    @Override
//    @Transactional
//    public boolean imageUpload(String id, ImageUploadDTO file) {
////        String a = userRepository.imageUpload(id, file);
//        String imageId = userPathRepository.imageUpload(new User());
//
//        List<String> paths = new ArrayList<>();
//        for(MultipartFile file1: file.getFileName()){
//            paths.add(imageUtils.makePath(ImageUtils.PROFILE, file1));
//        }
//        for(String path: paths){
//            if(userPathRepository.imageUpload(new ImagePathDAO(imageId, path)) == null){
//                return false;
//            }
//        }
//
//        int i = 0;
//        for (MultipartFile image : file.getFileName()){
//            try{
//                imageUtils.store(image, new File(paths.get(i++)));
//            } catch (IOException e){
//                return false;
//            }
//        }
//        return true;
//
//    }


    @Override
    @Transactional
    public String showImage(String userId) {
        return userPathRepository.showImage(userId);
    }

    @Override
    @Transactional
    public boolean deleteImage(String userId, String path) {
        if(userPathRepository.findById(userId, path)){
            return false;
        }

        List<String> paths = userPathRepository.find(userId);

        if(!userPathRepository.deleteImage(userId)){
            return false;
        }

        for(String path1: paths){
            imageUtils.delete(path1);
        }

        return true;
    }
}
