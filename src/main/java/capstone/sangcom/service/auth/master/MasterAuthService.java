package capstone.sangcom.service.auth.master;

import capstone.sangcom.entity.dto.masterSection.RegisteredStudentDTO;
import capstone.sangcom.entity.dto.masterSection.UpdateStudentRoleDTO;
import capstone.sangcom.entity.dto.userSection.auth.AuthStudentDTO;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dao.auth.AuthStudentDAO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface MasterAuthService {

    public boolean insertStudent(MultipartFile file) throws IOException;
    public List<AuthStudentDAO> getStudents();
    public List<RegisteredStudentDTO> getRegisteredStudent();
    public boolean updateStudentRole(UpdateStudentRoleDTO updateStudentRoleDTO);
    public boolean deleteStudent(ArrayList<AuthStudentDTO> list);

}
