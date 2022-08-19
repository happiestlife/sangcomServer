package capstone.sangcom.service.todo;

import capstone.sangcom.entity.dto.todoSection.InsertTodoListDTO;
import capstone.sangcom.entity.dto.todoSection.TodoDTO;
import capstone.sangcom.entity.dto.todoSection.UpdateTodoDTO;
import capstone.sangcom.repository.todo.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;


    @Override
    @Transactional
    public List<TodoDTO> getTodolist() {
        return null;
    }

    @Override
    @Transactional
    public boolean insert(String userId, String type, InsertTodoListDTO insertData) {


        return false;
    }

    @Override
    @Transactional
    public boolean update(String userId, int todoId, UpdateTodoDTO updataData) {
        return false;
    }

    @Override
    @Transactional
    public boolean delete() {
        return false;
    }

    @Override
    @Transactional
    public boolean check() {
        return false;
    }
}
