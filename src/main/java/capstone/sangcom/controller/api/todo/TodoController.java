package capstone.sangcom.controller.api.todo;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.controller.api.response.todo.CreateTodoResponse;
import capstone.sangcom.controller.api.response.todo.GetTodoListResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.User;
import capstone.sangcom.entity.dto.todoSection.*;
import capstone.sangcom.service.todo.TodoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//@CrossOrigin
//@AllArgsConstructor
@RestController
@RequestMapping("/api/school")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    /**
     * todo-list 날짜별 조회
     */
//    @RequestMapping(value="/todo?year={year}&month={month}&day={day}", method = {RequestMethod.GET, RequestMethod.POST})
    @GetMapping("/todo")
    public ResponseEntity<GetTodoListResponse> getTodolist(HttpServletRequest request,
                                                           @RequestParam int year,
                                                           @RequestParam int month,
                                                           @RequestParam int day){
        JwtUser user = (JwtUser) request.getAttribute("user"); // const user_id = req.body.data.id;

        List<GetTodolistResponseDTO> getTodolistResponseDTOS = todoService.getTodolist(user.getId(), year, month, day);

        return ResponseEntity
                .ok(new GetTodoListResponse(true, getTodolistResponseDTOS)); // Api명세서 - 성공응답 메세지
    }

    /**
     * todo-list 할 일 등록
     */
    @PostMapping("/todo")
    public ResponseEntity<CreateTodoResponse> insertTodoList(HttpServletRequest request,
                                                             @RequestBody InsertTodoListDTO insertTodoListDTO){
//        SimpleResponse는 성공응답 메세지에 true값만 반환한다.
//        const { body, year, month, day } = todoSchema.validateSync(req.body); //= @RequestBody 인 것 같음

        JwtUser user = (JwtUser) request.getAttribute("user"); // javascript - const user_id = req.body.data.id;
//        User user = (User) request.getAttribute("user"); // javascript - const user_id = req.body.data.id;

        int todoId = todoService.insertTodolist(user.getId(), insertTodoListDTO);
        if (todoId != -1)
            return ResponseEntity
                    .ok(new CreateTodoResponse(true, todoId));
        else
            return ResponseEntity
                    .ok(new CreateTodoResponse(false, -1));
    }

    /**
     * todo-list 할 일 수정
     */
    @PutMapping("/todo/{list_id}")
    public ResponseEntity<SimpleResponse> updateTodoList(HttpServletRequest request,
                                                         @PathVariable int list_id,
                                                         @RequestBody UpdateTodoListDTO updateTodoListDTO){ // @PathVariable 써야하나?
        JwtUser user = (JwtUser) request.getAttribute("user");

        if (todoService.updateTodolist(user.getId(), list_id, updateTodoListDTO))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SimpleResponse(false));
    }

    /**
     * todo-list 할 일 삭제
     */
    @DeleteMapping("/todo/{list_id}")
    public ResponseEntity<SimpleResponse> deleteTodoList(HttpServletRequest request,
                                                         @PathVariable int list_id){
        JwtUser user = (JwtUser) request.getAttribute("user");

        if(todoService.deleteTodolist(user.getId(), list_id))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SimpleResponse(false));

    }

    /**
     * todo-list 할 일 체크
     */
    @GetMapping("/todo/{list_id}")
    public ResponseEntity<SimpleResponse> checkTodoList(HttpServletRequest request,
                                                        @PathVariable int list_id,
                                                        @RequestBody ListCheckDTO listCheckDTO){
        JwtUser user = (JwtUser) request.getAttribute("user");

        if(todoService.checkTodolist(user.getId(), list_id, listCheckDTO))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new SimpleResponse(false));

    }

}

//Code 200
//        {
//        "todoList": [
//        {
//        "user_id": string,
//        "list_id": integer,
//        "body": string,
//        "listCheck": integer,
//        "year": integer,
//        "month": integer,
//        "day": integer
//        }
//        ],
//        "success" : true
//        }

//@RestController // 해당 Class는 REST API를 처리하는 Controller
//@RequestMapping("/api") // RequestMapping은 URL을 지정해주는 Annotation
//public class ControllerExample {
//
//    @GetMapping("/hello") // http://localhost:8080/api/hello
//    public String hello(){
//        return "hello spring boot!";
//    }
//
//}