package capstone.sangcom.repository.schedule;

import capstone.sangcom.entity.dto.scheduleSection.ScheduleInputDTO;
import capstone.sangcom.entity.dto.scheduleSection.ScheduleOutputDTO;

//import javax.ejb.Schedule;
import java.util.List;

public interface ScheduleRepository {

    public List<ScheduleOutputDTO> getScheduleData(ScheduleInputDTO scheduleInputDTO);
}
