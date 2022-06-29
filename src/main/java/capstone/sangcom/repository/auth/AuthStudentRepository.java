package capstone.sangcom.repository.auth;

import capstone.sangcom.repository.dao.AuthStudentDAO;

public interface AuthStudentRepository {

    public AuthStudentDAO insert(AuthStudentDAO authStudentDAO);
    public AuthStudentDAO find(AuthStudentDAO authStudentDAO);
    public boolean delete(AuthStudentDAO authStudentDAO);

}
