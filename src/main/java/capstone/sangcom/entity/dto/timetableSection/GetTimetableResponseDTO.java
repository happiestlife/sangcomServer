package capstone.sangcom.entity.dto.timetableSection;

import lombok.Data;

@Data
public class GetTimetableResponseDTO {

//    private final List<DaysDTO> timetableResponse;
    private final String subject;
    private final String days;
    private final Number period;
    private final String location;
    private final String teacher;
}
