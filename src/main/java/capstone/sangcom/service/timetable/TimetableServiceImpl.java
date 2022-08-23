package capstone.sangcom.service.timetable;

import capstone.sangcom.entity.dto.timetableSection.TimetableDTO;
import capstone.sangcom.repository.timetable.TimetableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService{

    private final TimetableRepository timetableRepository;

    @Override
    public boolean insertTimetable(TimetableDTO timetableDTo) {
        return false;
    }

    @Override
    public List<TimetableDTO> getTimetable(TimetableDTO timetableDTo) {
        return null;
    }

    @Override
    public boolean deleteTimetable(TimetableDTO timetableDTO) {
        return false;
    }
}
