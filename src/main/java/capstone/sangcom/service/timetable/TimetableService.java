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
    public List<GetTimetableResponseDTO> getTimetable(String user_id);


    // 시간표 삭제
    public boolean deleteTimetable(String user_id, DeleteTimetableDTO deleteTimetableDTO);
}
