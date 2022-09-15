package capstone.sangcom.service.todo;

import capstone.sangcom.entity.dto.todoSection.*;
import capstone.sangcom.repository.todo.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    @Override
    @Transactional         // 날짜별 todolist 조회
    public List<GetTodolistResponseDTO> getTodolist(String user_id, int year, int month, int day) {
        return todoRepository.getTodoList(user_id, year, month, day);
    }


    @Override               // todolist 삽입
    @Transactional         // todo 저장 후 todo Body 가져오기
    public boolean insertTodolist(String user_id, InsertTodoListDTO insertTodoListDTO) {
        if(!todoRepository.insertTodolist(user_id, insertTodoListDTO))
            return false;
        else
            return true;
//        return todoRepository.insertTodolist(user_id, insertTodoListDTO);
    }
// reportRepository.reportBoard 값을 받을 건데, 그거를 ReportBoardDAO로 받아줄 거고,
// ReportBoardDAO 클래스를 가져왔고, DAO 안에 들어갈 값들을 또 적어준 것

//    private final String user_id;
//    private final int list_id;
//    private final String body;
//    private final int listCheck;
//    private final int year;
//    private final int month;
//    private final int day;

    @Override
    @Transactional
    public boolean updateTodolist(String userId, int list_id, UpdateTodoListDTO updateTodoListDTO) {
        if(!todoRepository.updateTodoList(userId, list_id, updateTodoListDTO))
            return false;

        return true;
    }

    @Override
    @Transactional
    public boolean deleteTodolist(String userId, int list_id) {
//        // 게시글을 작성한 사용자가 맞는지 확인 -> 서비스 계층에서 구현
//        if(todoRepository.isUserWriteTodoList(userId, listId))
//            return false;

        if(!todoRepository.deleteTodoList(userId,list_id))
            return false;

        return true;
    }

    @Override
    @Transactional
    public boolean checkTodolist(String userId, int list_id, ListCheckDTO listCheckDTO) {

        if(!todoRepository.checkTodoList(userId, list_id, listCheckDTO))
            return false;

        return true;
    }
}
