package capstone.sangcom.repository.device;

public interface DeviceRepository {
    public boolean insert(String userId, String deviceId);
    public String getDeviceId(String userId);
    public boolean update(String userId, String deviceId);
    public boolean delete(String userId);
}
