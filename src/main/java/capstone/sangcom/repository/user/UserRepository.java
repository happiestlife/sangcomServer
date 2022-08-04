package capstone.sangcom.repository.user;

import capstone.sangcom.dto.userSection.info.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;
import capstone.sangcom.entity.UserDTO;

import java.util.List;

public interface UserRepository{

    public User insert(User user);
    public User findById(String id);
    public List<User> findAll();
    public boolean update();
    public boolean update(String id, String password);
    public boolean update(String id, UpdateUserInfoDTO updateUserInfoDTO);
    public boolean delete(String id);

}
