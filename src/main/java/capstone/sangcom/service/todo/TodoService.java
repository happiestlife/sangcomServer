package capstone.sangcom.service.todo;

import capstone.sangcom.entity.UserRole;
import capstone.sangcom.entity.dto.todoSection.GetTodoListDTO;
import capstone.sangcom.entity.dto.todoSection.InsertTodoListDTO;
import capstone.sangcom.entity.dto.todoSection.UpdateTodoListDTO;

import java.util.List;


public interface TodoService {
    public List<GetTodoListDTO> getTodolist(String userId, GetTodoListDTO getTodoListDTO); // 성공 응답 메시지 -  todoList, true

    public boolean insert(String userId, InsertTodoListDTO insertTodoListDTO); // 성공 응답 메시지 - true

    public boolean update(String userId, UpdateTodoListDTO updataData); // 성공 응답 메시지 - true

    public boolean delete(String userId, int listId); // 성공 응답 메시지 - true

    public boolean check(boolean listCheck, String userId, int listId); // 성공 응답 메시지 - true
//    public List<TodoDTO> insertTodoList(String body, int year, int month, int day);

//    public List<>
}


/**
 * 구현해야하는 메소드들 by SocialProject
 * insertTodoList
 * getTodoList
 * updateTodoList
 * deleteTodoList
 * checkTodoList
 *
 * user_id, body, check, year, month, day
 */