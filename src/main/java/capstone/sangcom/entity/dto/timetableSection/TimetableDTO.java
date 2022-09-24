package capstone.sangcom.entity.dto.timetableSection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimetableDTO {

    private String subject;
    private String days;
    private Number period;
    private String location;
    private String teacher;
}
