package capstone.sangcom.controller.api.master;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.entity.dto.userSection.auth.AuthStudentDTO;
import capstone.sangcom.service.auth.master.MasterAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/auth")
public class DeleteStudentController {

    private final MasterAuthService masterAuthService;

    /**
     * 학번 삭제
     */
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
}
