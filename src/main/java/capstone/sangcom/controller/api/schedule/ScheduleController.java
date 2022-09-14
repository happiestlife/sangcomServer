package capstone.sangcom.controller.api.schedule;


import capstone.sangcom.controller.api.response.schedule.ScheduleResponse;
import capstone.sangcom.entity.dto.scheduleSection.ScheduleInputDTO;
import capstone.sangcom.entity.dto.scheduleSection.ScheduleOutputDTO;
import capstone.sangcom.service.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/school")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 학사일정 조회 Controller
     */
    @GetMapping("/schedule?AA_YMD={날짜값(ex.20210510)}")
    public ResponseEntity<ScheduleResponse> getSchedule(@PathVariable ScheduleInputDTO scheduleInput){ // AA_YMD(날짜값) 입력

        // ~~~
        // getSchoolInfo의 출력 코드 (SCHUL_NM: "상명대학교사범대학부속여자고등학교)

        List<ScheduleOutputDTO> scheduleOutput = scheduleService.getSchedule(scheduleInput);
        // Service의 getSchedule 메소드에 "매개변수 scheduleInput(=AA_YMD) 값"에 해당하는 0~n개의 outout을 반환한다.

        return ResponseEntity
                .ok(new ScheduleResponse(true, scheduleOutput));
        // 성공 응답 메시지를 반환한다.

    }
}
