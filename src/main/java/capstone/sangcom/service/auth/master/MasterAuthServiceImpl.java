package capstone.sangcom.service.auth.master;

import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.repository.dao.AuthStudentDAO;

import java.util.List;

public class MasterAuthServiceImpl implements MasterAuthService{



    @Override
    public boolean insertStudent() {
        return false;
    }

    @Override
    public boolean checkStudent(String name, String studentId) {
        return false;
    }

    @Override
    public List<AuthStudentDAO> getStudents() {
        return null;
    }

    @Override
    public boolean deleteStudent(String name, String studentId) {
        return false;
    }

    @Override
    public List<JwtUser> getRegisteredStudent() {
        return null;
    }
}
