package capstone.sangcom.controller.api.master;

import capstone.sangcom.controller.api.response.master.RegisteredStudentResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dao.auth.AuthStudentDAO;
import capstone.sangcom.entity.dto.masterSection.RegisteredStudentDTO;
import capstone.sangcom.service.auth.master.MasterAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/auth")
public class ReadStudentController {

    private final MasterAuthService masterAuthService;

    /**
     * 등록한 학생정보(학번) 전체조회
     */
    @GetMapping("/student")
    public ResponseEntity<List<AuthStudentDAO>> getAuthStudents() {
        List<AuthStudentDAO> students = masterAuthService.getStudents();

        if (students != null)
            return ResponseEntity
                    .ok(students);
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
    }

    /**
     * 회원가입한 모든 학생 정보 조회
     */
    @GetMapping("/master/student")
    public ResponseEntity<RegisteredStudentResponse> getAllUser() {
        List<RegisteredStudentDTO> studentsList = masterAuthService.getRegisteredStudent();

        if (studentsList != null)
            return ResponseEntity
                    .ok(new RegisteredStudentResponse(true, studentsList));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new RegisteredStudentResponse(false, null));
    }
}
