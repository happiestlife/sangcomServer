package capstone.sangcom.service.auth.master;

import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.UserRole;
import capstone.sangcom.repository.dao.AuthStudentDAO;

import java.util.List;

public interface MasterAuthService {

    public boolean insertStudent();
    public boolean checkStudent(String name, String studentId);
    public List<AuthStudentDAO> getStudents();
    public boolean deleteStudent(String name, String studentId);
    public List<JwtUser> getRegisteredStudent();

}
