package capstone.sangcom.service.auth.master;

import capstone.sangcom.dto.userSection.auth.AuthStudentDTO;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.repository.dao.auth.AuthStudentDAO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface MasterAuthService {

    public boolean insertStudent(MultipartFile file) throws IOException;
    public List<AuthStudentDAO> getStudents();
    public boolean deleteStudent(ArrayList<AuthStudentDTO> list);
    public List<JwtUser> getRegisteredStudent();

}
