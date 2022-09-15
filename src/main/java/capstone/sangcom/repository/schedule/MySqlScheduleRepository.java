package capstone.sangcom.repository.schedule;

import capstone.sangcom.entity.dto.scheduleSection.ScheduleInputDTO;
import capstone.sangcom.entity.dto.scheduleSection.ScheduleOutputDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySqlScheduleRepository implements ScheduleRepository{

    @Override
    public List<ScheduleOutputDTO> getScheduleData(ScheduleInputDTO scheduleInputDTO) {
        return null;
    }
}
