package capstone.sangcom.repository.todo;

import capstone.sangcom.entity.dto.todoSection.*;
//import org.springframework.stereotype.Repository;

import java.util.List;


//@Repository
public interface TodoRepository {

    // 할 일 조회
    public List<GetTodolistResponseDTO> getTodoList(String user_id, int year, int month, int day);

    // 할 일 등록
    public boolean insertTodolist(String user_id, InsertTodoListDTO insertTodoListDTO);

    // 할 일 수정
    public List<GetTodolistResponseDTO> searchTodoList(String user_id, int list_id);
    public boolean updateTodoList(String user_id, int list_id, UpdateTodoListDTO updateTodoListDTO);

    // 할 일 삭제
    public boolean deleteTodoList(String user_id, int list_id);
//    public boolean isUserWriteTodoList(String userId, int listId);

    // 할 일 체크
    public boolean checkTodoList(String user_id, int list_id, ListCheckDTO listCheckDTO);

}
