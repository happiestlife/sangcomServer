package capstone.sangcom.service.todo;

import capstone.sangcom.entity.dto.todoSection.InsertTodoListDTO;
import capstone.sangcom.entity.dto.todoSection.TodoDTO;
import capstone.sangcom.entity.dto.todoSection.UpdateTodoDTO;

import java.util.List;

public interface TodoService {
    public List<TodoDTO> getTodolist();

    public boolean insert(String userId, String type, InsertTodoListDTO insertData);

    public boolean update(String userId, int todoId, UpdateTodoDTO updataData);

    public boolean delete();

    public boolean check();
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