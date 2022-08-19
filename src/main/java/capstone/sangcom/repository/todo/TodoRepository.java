package capstone.sangcom.repository.todo;

import capstone.sangcom.entity.dto.todoSection.TodoDTO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TodoRepository {

    public int insert(TodoDTO todoDTO);
    public List<TodoDTO> getTodoList(String user_id, TodoDTO todoDTO);
    public List<TodoDTO> searchTodoList(String user_id, int list_id);
    public boolean updateTodoList(String body, String user_id, int list_id);
    public boolean deleteTodoList(String user_id, int list_id);
    public boolean checkTodoList(boolean listCheck, String user_id, int list_id);

}
