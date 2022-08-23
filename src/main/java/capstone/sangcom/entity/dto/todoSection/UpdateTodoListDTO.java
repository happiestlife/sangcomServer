package capstone.sangcom.entity.dto.todoSection;

import lombok.Data;


@Data
public class UpdateTodoListDTO {

    private final String body;
    private final String user_id;
    private final int list_id;
}
