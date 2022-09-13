package capstone.sangcom.repository.timetable;

import capstone.sangcom.entity.dto.timetableSection.TimetableDTO;
import capstone.sangcom.entity.dto.timetableSection.DeleteTimetableDTO;
import capstone.sangcom.entity.dto.timetableSection.GetTimetableResponseDTO;

import java.util.List;

public interface TimetableRepository {

    // 시간표 등록
    public boolean insertTimetable(String user_id, TimetableDTO timetableDTO);

    // 시간표 수정
//    public List<TimetableDTO> selectTimetable(String user_id, String days, Number period);
//    public boolean updateTimetable(String user_id, String days, Number period, TimetableDTO timetableDTO);

    // 시간표 조회
    public List<GetTimetableResponseDTO> getTimetable(String user_id); // API명세서에 파라미터가 없음..

    // 시간표 삭제
    public boolean deleteTimetable(String user_id, DeleteTimetableDTO deleteTimetableDTO);

}
