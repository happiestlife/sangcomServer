package capstone.sangcom.service.todo;

import capstone.sangcom.entity.dto.todoSection.*;

import java.util.List;


public interface TodoService {
    // 할 일 조회
    public List<GetTodolistResponseDTO> getTodolist(String user_id, int year, int month, int day); // 성공 응답 메시지 -  todoList, true

    // 할 일 등록
    public int insertTodolist(String user_id, InsertTodoListDTO insertTodoListDTO); // 성공 응답 메시지 - true

    // 할 일 수정
    public boolean updateTodolist(String user_id, int list_id, UpdateTodoListDTO updateTodoListDTO); // 성공 응답 메시지 - true

    // 할 일 삭제
    public boolean deleteTodolist(String user_id, int list_id); // 성공 응답 메시지 - true

    // 할 일 체크
    public boolean checkTodolist(String user_id, int list_id, ListCheckDTO listCheckDTO); // 성공 응답 메시지 - true
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