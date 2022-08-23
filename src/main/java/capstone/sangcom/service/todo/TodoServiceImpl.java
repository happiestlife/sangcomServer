package capstone.sangcom.service.todo;

import capstone.sangcom.entity.UserRole;
import capstone.sangcom.entity.dao.todo.InsertTodoListDAO;
import capstone.sangcom.entity.dto.todoSection.GetTodoListDTO;
import capstone.sangcom.entity.dto.todoSection.InsertTodoListDTO;
import capstone.sangcom.entity.dto.todoSection.UpdateTodoListDTO;
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
    @Transactional
    public List<GetTodoListDTO> getTodolist(String userId, GetTodoListDTO getTodoListDTO) {
        // 날짜별 todolist 조회

        return todoRepository.getTodoList(userId, getTodoListDTO);
    }

    @Override
    @Transactional
    public boolean insert(String user_id, InsertTodoListDTO insertTodoListDTO) {
        // todo 저장 후 todo Body 가져오기
        if(todoRepository.insert(user_id, insertTodoListDTO))
            return false;
        else
            return true;
//        return todoRepository.insert(user_id, insertTodoListDTO);
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
    public boolean update(String userId, UpdateTodoListDTO updateTodoListDTO) {
        if(!todoRepository.updateTodoList(userId, updateTodoListDTO))
            return false;

        return true;
    }

    @Override
    @Transactional
    public boolean delete(String userId, int listId) {
//        // 게시글을 작성한 사용자가 맞는지 확인 -> 서비스 계층에서 구현
//        if(todoRepository.isUserWriteTodoList(userId, listId))
//            return false;

        if(!todoRepository.deleteTodoList(userId,listId))
            return false;

        return true;
    }

    @Override
    @Transactional
    public boolean check(boolean listCheck, String userId, int listId) {

        if(!todoRepository.checkTodoList(listCheck, userId,listId))
            return false;

        return true;
    }
}
