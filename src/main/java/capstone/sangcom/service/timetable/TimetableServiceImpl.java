package capstone.sangcom.service.timetable;

import capstone.sangcom.entity.dto.timetableSection.TimetableDTO;
import capstone.sangcom.entity.dto.timetableSection.DeleteTimetableDTO;
import capstone.sangcom.entity.dto.timetableSection.GetTimetableResponseDTO;
import capstone.sangcom.repository.timetable.TimetableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<List<TimetableDTO>>getTimetableAtWeb(String user_id) {
        List<List<TimetableDTO>> timeTable = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            List<TimetableDTO> tResult = timetableRepository.getTimetable(user_id, i);

            ArrayList<TimetableDTO> row = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                row.add(new TimetableDTO("", null, null, null, null));
            }
            for (TimetableDTO t : tResult) {
                int colInd = 0;
                String days = t.getDays();
                if(days.equals("월")){
                    colInd = 0;
                }
                else if (days.equals("화")) {
                    colInd = 1;
                }
                else if (days.equals("수")) {
                    colInd = 2;
                }
                else if (days.equals("목")) {
                    colInd = 3;
                }
                else if (days.equals("금")) {
                    colInd = 4;
                }

                TimetableDTO findTimeTableRow = row.get(colInd);
                findTimeTableRow.setSubject(t.getSubject());
                findTimeTableRow.setDays(t.getDays());
                findTimeTableRow.setPeriod(t.getPeriod());
                findTimeTableRow.setTeacher(t.getTeacher());
                findTimeTableRow.setLocation(t.getLocation());

            }

            timeTable.add(row);
        }
        return timeTable;
    }

    @Override
    @Transactional // 시간표 조회
    public List<List<TimetableDTO>> getTimetableAtApi(String user_id) {
        List<List<TimetableDTO>> timeTable = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            timeTable.add(timetableRepository.getTimetable(user_id, i));
        }
        return timeTable;

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
