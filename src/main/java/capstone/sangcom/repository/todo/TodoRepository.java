package capstone.sangcom.repository.todo;

import capstone.sangcom.entity.dto.todoSection.InsertTodoListDTO;
import capstone.sangcom.entity.dto.todoSection.GetTodolistResponseDTO;
import capstone.sangcom.entity.dto.todoSection.UpdateTodoListDTO;
//import org.springframework.stereotype.Repository;

import java.util.List;


//@Repository
public interface TodoRepository {

    public List<GetTodolistResponseDTO> getTodoList(String user_id);

    // 할 일 등록
    public boolean insertTodolist(String user_id, InsertTodoListDTO insertTodoListDTO);

    public List<GetTodolistResponseDTO> searchTodoList(String user_id, int list_id);
    public boolean updateTodoList(String body, UpdateTodoListDTO updateTodoListDTO);

    public boolean deleteTodoList(String user_id, int listId);
//    public boolean isUserWriteTodoList(String userId, int listId);

    public boolean checkTodoList(boolean listCheck, String user_id, int list_id);

}
