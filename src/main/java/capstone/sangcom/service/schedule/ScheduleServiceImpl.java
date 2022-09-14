package capstone.sangcom.service.schedule;


import capstone.sangcom.entity.dto.scheduleSection.ScheduleInputDTO;
import capstone.sangcom.entity.dto.scheduleSection.ScheduleOutputDTO;
import capstone.sangcom.repository.schedule.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    @Override
    @Transactional
    public List<ScheduleOutputDTO> getSchedule(ScheduleInputDTO scheduleInput) {
        return scheduleRepository.getScheduleData(scheduleInput);
    }

    @Override
    @Transactional
    public boolean getScheduleOK() {
        return true;
    }
}
