package capstone.sangcom.repository.user;

import capstone.sangcom.entity.dto.userSection.info.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;

import java.util.*;

//@Repository
public class MemoryUserRepository implements UserRepository{

    private Map<String, User> repository;

    public MemoryUserRepository() {
        this.repository = new HashMap<>();
    }

    @Override
    public User insert(User user){
        if (!repository.containsKey(user.getId())) {
            repository.put(user.getId(), user);

            return repository.get(user.getId());
        }else
            return null;
    }

    @Override
    public User findById(String id) {
        if(repository.containsKey(id))
            return repository.get(id);
        else
            return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = null;

        for (String id : repository.keySet()) {
            users.add(repository.get(id));
        }

        return users;
    }

    @Override
    public boolean update(String id, UpdateUserInfoDTO updateUserInfoDTO) {
        User user = repository.get(id);
        if (user != null) {
            user.setPhone(updateUserInfoDTO.getPhone());
            user.setSchoolgrade(updateUserInfoDTO.getSchoolgrade());
            user.setSchoolclass(updateUserInfoDTO.getSchoolclass());
            user.setSchoolnumber(updateUserInfoDTO.getSchoolnumber());
            user.setBirth(updateUserInfoDTO.getBirth());
            user.setYear(updateUserInfoDTO.getYear());
            user.setEmail(updateUserInfoDTO.getEmail());

            repository.put(id, user);
            return true;
        }else
            return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean update(String id, String password) {
        if (repository.containsKey(id)) {
            User user = repository.get(id);
            user.setPassword(password);

            repository.put(id, user);
            return true;
        }else
            return false;
    }

    @Override
    public boolean delete(String id) {
        if (repository.containsKey(id)) {
            repository.remove(id);

            return true;
        }else
            return false;
    }

    public void removeAll() {
        repository.clear();
    }

    public List<User> selectAll() {
        List<User> list = new ArrayList<User>();
        for (String s : repository.keySet()) {
            list.add(repository.get(s));
        };

        return list;
    }

}
