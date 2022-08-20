package capstone.sangcom.service.device;

import java.util.List;

public interface DeviceService {
    public boolean registerDevice(String userId, String deviceId);
    public List<String> getRegisteredDevices();
    public String getRegisteredDevice(String userId);
    public boolean deleteDevice(String userId);
}
