package capstone.sangcom.repository.auth;

import capstone.sangcom.repository.dao.AuthStudentDAO;

import java.util.List;

public interface AuthStudentRepository {

    public AuthStudentDAO insert(AuthStudentDAO authStudentDAO);
    public AuthStudentDAO find(AuthStudentDAO authStudentDAO);
    public List<AuthStudentDAO> findAll();
    public boolean delete(AuthStudentDAO authStudentDAO);

}
