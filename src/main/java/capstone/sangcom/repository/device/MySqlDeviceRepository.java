package capstone.sangcom.repository.device;

import lombok.RequiredArgsConstructor;
import org.bouncycastle.crypto.util.DEROtherInfo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MySqlDeviceRepository implements DeviceRepository {

    private final String DEVICE_TABLE = "devices";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public boolean insert(String userId, String deviceId) {
        String query = "INSERT INTO " + DEVICE_TABLE + " VALUES(:user_id, :device_id)";
        return decideUpdateSuccess(jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("user_id", userId)
                        .addValue("device_id", deviceId)));
    }

    @Override
    public String getDeviceId(String userId) {
        String query = "SELECT device_id FROM " + DEVICE_TABLE + " WHERE user_id = :user_Id";
        List<String> result = jdbcTemplate.query(query,
                new MapSqlParameterSource()
                        .addValue("user_id", userId),
                (rs, rowNum) -> rs.getString("device_id"));

        if(!result.isEmpty())
            return result.get(0);
        else
            return null;
    }

    @Override
    public boolean update(String userId, String deviceId) {
        String query = "UPDATE " + DEVICE_TABLE + " SET device_id = :device_id WHERE user_Id = :user_id";
        return decideUpdateSuccess(jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("device_id", deviceId)
                        .addValue("user_id", userId)));
    }

    @Override
    public boolean delete(String userId) {
        String query = "DELETE FROM " + DEVICE_TABLE + " WHERE user_Id = :user_id";
        return decideUpdateSuccess(jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("user_id", userId)));
    }

    private boolean decideUpdateSuccess(int effectedCount) {
        if(effectedCount == 1)
            return true;
        else
            return false;
    }
}
