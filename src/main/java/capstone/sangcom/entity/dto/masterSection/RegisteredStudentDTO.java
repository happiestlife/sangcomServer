package capstone.sangcom.entity.dto.masterSection;

import lombok.Data;

@Data
public class RegisteredStudentDTO {
    private final String id;
    private final String name;
    private final String role;
    private final int schoolgrade;
    private final int schoolclass;
    private final int schoolnumber;
}
