package capstone.sangcom.service.auth.student;

import capstone.sangcom.entity.dto.userSection.auth.AuthStudentDTO;
import capstone.sangcom.repository.auth.AuthStudentRepository;
import capstone.sangcom.entity.dao.auth.AuthStudentDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentAuthServiceImpl implements StudentAuthService{

    private final AuthStudentRepository authStudentRepository;

    @Override
    public boolean studentAuthorization(AuthStudentDTO authStudentDTO) {
        if(authStudentRepository.find(new AuthStudentDAO(authStudentDTO.getStudentId(), authStudentDTO.getName())) != null)
            return true;
        else
            return false;
    }
}
