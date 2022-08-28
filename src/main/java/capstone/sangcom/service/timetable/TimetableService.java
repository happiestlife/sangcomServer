package capstone.sangcom.service.timetable;

import capstone.sangcom.entity.dto.timetableSection.TimetableDTO;

import java.util.List;

public interface TimetableService {

    // 시간표 등록
//    public List<TimetableDTO> insertTimetable(TimetableDTO timetableDTO);
    public boolean insertTimetable(String user_id, TimetableDTO timetableDTO);

    // 시간표 조회
    public List<TimetableDTO> getTimetable();

    // 시간표 삭제
    public boolean deleteTimetable(String days, Number period);
}
