package capstone.sangcom.entity.dto.todoSection;

import lombok.Data;

@Data
public class InsertTodoListDTO {

    //왜 int가 아니라 string이지?
    private final String year;
    private final String month;
    private final String day;
    private final String body;
}
