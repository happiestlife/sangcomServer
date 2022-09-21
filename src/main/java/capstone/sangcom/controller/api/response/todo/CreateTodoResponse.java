package capstone.sangcom.controller.api.response.todo;

import lombok.Data;

@Data
public class CreateTodoResponse {

    private final boolean success;
    private final int todoId;

}
