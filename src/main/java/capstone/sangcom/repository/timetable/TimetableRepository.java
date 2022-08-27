package capstone.sangcom.repository.timetable;

import capstone.sangcom.entity.dto.timetableSection.TimetableDTO;

import java.util.List;

public interface TimetableRepository {

    // 시간표 등록, 수정
    public boolean insertTimetable(TimetableDTO timetableDTO);
    public boolean updateTimetable(TimetableDTO timetableDTO);

    // 시간표 조회
    public List<TimetableDTO> getTimetable(); //(int user_id, TimetableDTO timetableDTO); API명세서에 파라미터가 없음..

    // 시간표 삭제
    public boolean deleteTimetable(String days, Number period);

}
