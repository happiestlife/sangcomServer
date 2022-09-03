package capstone.sangcom.controller.api.response.timetable;

import capstone.sangcom.entity.dto.timetableSection.GetTimetableResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TimetableResponse {

    private boolean success;
    private List<GetTimetableResponseDTO> timetableResponseDTO;
}
