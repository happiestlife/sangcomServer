package capstone.sangcom.entity.dto.todoSection;

import lombok.Data;

@Data
public class InsertTodoListDTO {

    private final String body;
    private final int year;
    private final int month;
    private final int day;
}
