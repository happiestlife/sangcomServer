package capstone.sangcom.controller.api.master;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.entity.dto.masterSection.UpdateStudentRoleDTO;
import capstone.sangcom.service.auth.master.MasterAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UpdateStudentController {

    private final MasterAuthService masterAuthService;

    @PutMapping("/update/role")
    public ResponseEntity<SimpleResponse> updateStudentRole(@RequestBody UpdateStudentRoleDTO updateStudentRoleDTO) {
        if(masterAuthService.updateStudentRole(updateStudentRoleDTO))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new SimpleResponse(false));
    }
}
