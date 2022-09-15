package capstone.sangcom.service.schedule;


import capstone.sangcom.entity.dto.scheduleSection.ScheduleInputDTO;
import capstone.sangcom.entity.dto.scheduleSection.ScheduleOutputDTO;

import java.util.List;

/**
 * API 명세서 - 성공 응답 메시지의 "메소드"
 *
 * 학사일정 조회의 비즈니스 로직에 어떤 메소드가 필요한지 알려주는 인터페이스
 */

public interface ScheduleService {

    public List<ScheduleOutputDTO> getSchedule(ScheduleInputDTO scheduleInput);
    // 날짜값을 받아서 Output을 List 형식으로 출력한다.

    public boolean getScheduleOK();
    // 성공 메시지를 반환한다.
}
