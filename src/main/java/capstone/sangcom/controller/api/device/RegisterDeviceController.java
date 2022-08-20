package capstone.sangcom.controller.api.device;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.deviceSection.RegisterDeviceDTO;
import capstone.sangcom.service.device.DeviceService;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/device")
@RequiredArgsConstructor
public class RegisterDeviceController {

    private final DeviceService deviceService;

    @PostMapping
    public ResponseEntity<SimpleResponse> registerDevice(HttpServletRequest request,
                                                    @RequestBody RegisterDeviceDTO registerDeviceDTO) {
        JwtUser user = (JwtUser) request.getAttribute("user");
        if(deviceService.registerDevice(user.getId(), registerDeviceDTO.getDeviceToken()))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new SimpleResponse(false));
    }

}
