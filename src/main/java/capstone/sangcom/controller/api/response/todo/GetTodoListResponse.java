package capstone.sangcom.controller.api.response.todo;

import capstone.sangcom.entity.dto.todoSection.GetTodolistResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class GetTodoListResponse {

    private boolean success;
    private List<GetTodolistResponseDTO> getTodolistResponseDTOS;

}
