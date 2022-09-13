package capstone.sangcom.service.timetable;

import capstone.sangcom.entity.dto.timetableSection.TimetableDTO;
import capstone.sangcom.entity.dto.timetableSection.DeleteTimetableDTO;
import capstone.sangcom.entity.dto.timetableSection.GetTimetableResponseDTO;
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
    @Transactional // 시간표 삽입
    public boolean insertTimetable(String user_id, TimetableDTO timetableDTO) {
        return timetableRepository.insertTimetable(user_id, timetableDTO);
    }

//    @Override
//    @Transactional // 시간표 수정
//    public boolean updateTimetable(String user_id, String days, Number period, TimetableDTO timetableDTO){
//        return timetableRepository.updateTimetable(user_id, days, period, timetableDTO);
//    }

    @Override
    @Transactional // 시간표 조회
    public List<GetTimetableResponseDTO> getTimetable(String user_id) {
        return timetableRepository.getTimetable(user_id); // API명세서에 파라미터 값이 없음.

    }
    // O

    @Override
    @Transactional // 시간표 삭제
    public boolean deleteTimetable(String user_id, DeleteTimetableDTO deleteTimetableDTO) {

        if(!timetableRepository.deleteTimetable(user_id, deleteTimetableDTO))
            return false;

        return true;
    }
    // BoardServiceImpl - "delete" 참고하여 작성함.
}
