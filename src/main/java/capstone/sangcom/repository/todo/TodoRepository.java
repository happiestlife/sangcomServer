package capstone.sangcom.repository.todo;

import capstone.sangcom.entity.dto.todoSection.GetTodoListDTO;
import capstone.sangcom.entity.dto.todoSection.InsertTodoListDTO;
import capstone.sangcom.entity.dto.todoSection.TodoDTO;
import capstone.sangcom.entity.dto.todoSection.UpdateTodoListDTO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TodoRepository {

    public boolean insert(String user_id, InsertTodoListDTO insertTodoListDTO);
    public List<GetTodoListDTO> getTodoList(String user_id, GetTodoListDTO getTodoListDTO);
    public List<TodoDTO> searchTodoList(String user_id, int list_id);
    public boolean updateTodoList(String body, UpdateTodoListDTO updateTodoListDTO);

    public boolean deleteTodoList(String user_id, int list_id);
//    public boolean isUserWriteTodoList(String userId, int listId);

    public boolean checkTodoList(boolean listCheck, String user_id, int list_id);

}
