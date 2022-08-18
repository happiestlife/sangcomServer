package capstone.sangcom.controller.api.device;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.deviceSection.RegisterDeviceDTO;
import capstone.sangcom.service.device.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/device")
@RequiredArgsConstructor
public class DeleteDeviceController {

    private final DeviceService deviceService;

    @DeleteMapping
    public ResponseEntity<SimpleResponse> registerDevice(HttpServletRequest request) {
        JwtUser user = (JwtUser) request.getAttribute("user");
        if(deviceService.deleteDevice(user.getId()))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new SimpleResponse(false));
    }
}
