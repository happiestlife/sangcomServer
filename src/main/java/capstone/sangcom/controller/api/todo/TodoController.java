package capstone.sangcom.controller.api.todo;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.todoSection.InsertTodoListDTO;
import capstone.sangcom.entity.dto.todoSection.TodoDTO;
import capstone.sangcom.controller.api.response.todo.TodoResponse;
import capstone.sangcom.entity.dto.todoSection.UpdateTodoDTO;
import capstone.sangcom.service.todo.TodoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/school/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    /**
     * todo-list 날짜별 조회
     */
    @GetMapping("?year={{year}}&month={{monthj}}&day={{day}}")
    public ResponseEntity<TodoResponse> getTodolist(){

        List<TodoDTO> todo = todoService.getTodolist();

        return ResponseEntity
                .ok(new TodoResponse(true, todo));
    }

    /**
     * todo-list 할 일 등록
     */
    @PostMapping("")
    public ResponseEntity<SimpleResponse> insertTodoList(HttpServletRequest request,
                                                  @RequestParam  String type, InsertTodoListDTO insertData){

        JwtUser user = (JwtUser) request.getAttribute("user");

        if (todoService.insert(user.getId(), type, insertData))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SimpleResponse(false));
    }

    /**
     * todo-list 할 일 수정
     */
    @PutMapping("/:list_id")
    public ResponseEntity<SimpleResponse> updateTodoList(HttpServletRequest request,
                                                  @PathVariable int todoId,
                                                  UpdateTodoDTO updataData){
        JwtUser user = (JwtUser) request.getAttribute("user");

        if (todoService.update(user.getId(), todoId, updataData))
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
    @DeleteMapping("/:list_id")
    public ResponseEntity<SimpleResponse> deleteTodoList(HttpServletRequest request,
                                                         @PathVariable int todoId){
        JwtUser user = (JwtUser) request.getAttribute("user");

        if(todoService.delete())
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
    @GetMapping("/:list_id")
    public ResponseEntity<SimpleResponse> checkTodoList(HttpServletRequest request,
                                                 @PathVariable int todoId){
        JwtUser user = (JwtUser) request.getAttribute("user");

        if(todoService.check())
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