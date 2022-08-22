package capstone.sangcom.controller.api.response.todo;

import capstone.sangcom.entity.dto.todoSection.GetTodoListDTO;
import capstone.sangcom.entity.dto.todoSection.TodoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class GetTodoListResponse {

    private boolean success;
    private List<GetTodoListDTO> data;

}
