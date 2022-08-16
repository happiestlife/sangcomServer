package capstone.sangcom.service.auth.student;


import capstone.sangcom.entity.dto.userSection.auth.AuthStudentDTO;

public interface StudentAuthService {

    public boolean studentAuthorization(AuthStudentDTO authStudentDTO);

}
