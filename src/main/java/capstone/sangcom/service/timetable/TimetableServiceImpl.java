package capstone.sangcom.service.timetable;

import capstone.sangcom.entity.dto.timetableSection.TimetableDTO;
import capstone.sangcom.repository.timetable.TimetableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService{

    private final TimetableRepository timetableRepository;

    @Override
    @Transactional
    public boolean insertTimetable(String user_id, TimetableDTO timetableDTO) {
        return timetableRepository.insertTimetable(user_id, timetableDTO);
    }


    @Override
    @Transactional
    public List<TimetableDTO> getTimetable() {
        return timetableRepository.getTimetable(); // API명세서에 파라미터 값이 없음.

    }
    // O

    @Override
    @Transactional
    public boolean deleteTimetable(String days, Number period) {

        if(!timetableRepository.deleteTimetable(days, period))
            return false;

        return true;
    }
    // BoardServiceImpl - "delete" 참고하여 작성함.
}
