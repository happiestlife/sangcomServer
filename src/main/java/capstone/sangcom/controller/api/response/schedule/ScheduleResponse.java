package capstone.sangcom.controller.api.response.schedule;


import capstone.sangcom.entity.dto.scheduleSection.ScheduleOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * API명세서 - 성공 응답 메시지의 "변수"
 */

@Getter
@Setter
@AllArgsConstructor
public class ScheduleResponse { 

    private boolean success;
    private List<ScheduleOutputDTO> scheduleData;
}


