package capstone.sangcom.entity.dto.timetableSection;

import lombok.Data;

@Data
public class TimetableDTO {

    private final String user_id;
    private final String subject;
    private final String days;
    private final Number period;
    private final String location;
    private final String teacher;
}
