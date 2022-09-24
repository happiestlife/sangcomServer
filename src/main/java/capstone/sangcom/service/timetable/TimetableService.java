package capstone.sangcom.service.timetable;

import capstone.sangcom.entity.dto.timetableSection.TimetableDTO;
import capstone.sangcom.entity.dto.timetableSection.DeleteTimetableDTO;
import capstone.sangcom.entity.dto.timetableSection.GetTimetableResponseDTO;

import java.util.List;

public interface TimetableService {

    // 시간표 등록
//    public List<TimetableDTO> insertTimetable(TimetableDTO timetableDTO);
    public boolean insertTimetable(String user_id, TimetableDTO timetableDTO);

    // 시간표 수정
//    public boolean updateTimetable(String user_id, String days, Number period, TimetableDTO timetableDTO);


    // 시간표 조회
    public List<List<TimetableDTO>>  getTimetableAtWeb(String user_id);
    public List<List<TimetableDTO>> getTimetableAtApi(String user_id);

    public TimetableDTO getTimetable(String userId, String day, int period);

    public boolean updateTimetable(String user_id, TimetableDTO timetableDTO);

    // 시간표 삭제
    public boolean deleteTimetable(String user_id, DeleteTimetableDTO deleteTimetableDTO);
}
