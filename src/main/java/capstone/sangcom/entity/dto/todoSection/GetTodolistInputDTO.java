package capstone.sangcom.entity.dto.todoSection;

import lombok.Data;

@Data
public class GetTodolistInputDTO {

    private final int year;
    private final int month;
    private final int day;
}
