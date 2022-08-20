package capstone.sangcom.service.device;

import capstone.sangcom.repository.device.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService{

    private final DeviceRepository deviceRepository;


    @Override
    public boolean registerDevice(String userId, String deviceId) {
        String registeredDeviceId = deviceRepository.getDeviceId(userId);
        // 등록된 기기가 없다면 새로 등록
        if (registeredDeviceId == null) {
            if(deviceRepository.insert(userId, deviceId) == false)
                return false;
        }
        // 등록된 기기가 있다면 update
        else {
            if(deviceRepository.update(userId, deviceId) == false)
                return false;
        }

        return true;
    }

    /**
     * 아래의 2 기능은 아직 필요 없다고 판단하여 구현 X
     */
    @Override
    public List<String> getRegisteredDevices() {
        return null;
    }

    @Override
    public String getRegisteredDevice(String userId) {
        return null;
    }

    @Override
    public boolean deleteDevice(String userId) {
        return deviceRepository.delete(userId);
    }
}
