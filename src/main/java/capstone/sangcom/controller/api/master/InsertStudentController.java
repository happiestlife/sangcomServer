package capstone.sangcom.controller.api.master;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.service.auth.master.MasterAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/auth")
public class InsertStudentController {

    private final MasterAuthService masterAuthService;

    /**
     *  학생정보(학번) 저장
     */
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
}
