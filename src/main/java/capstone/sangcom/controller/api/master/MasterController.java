package capstone.sangcom.controller.api.master;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.dto.auth.AuthStudentDTO;
import capstone.sangcom.entity.ExcelData;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.repository.dao.AuthStudentDAO;
import capstone.sangcom.service.auth.master.MasterAuthService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user/auth")
public class MasterController {

    private final MasterAuthService masterAuthService;

    @PostMapping("/student")
    public ResponseEntity<SimpleResponse> registerStudent(MultipartFile file) throws IOException {
        if (masterAuthService.insertStudent(file))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new SimpleResponse(false));
    }

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

    @DeleteMapping("/student")
    public ResponseEntity<SimpleResponse> deleteStudentAuth(@RequestBody ArrayList<AuthStudentDTO> list) {
        if (masterAuthService.deleteStudent(list))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new SimpleResponse(false));
    }

    @GetMapping("/student/all")
    public ResponseEntity<List<JwtUser>> getAllUser() {
        List<JwtUser> users = masterAuthService.getRegisteredStudent();

        if(users != null)
            return ResponseEntity
                    .ok(users);
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
    }
}
