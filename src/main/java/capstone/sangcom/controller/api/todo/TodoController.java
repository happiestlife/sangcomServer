package capstone.sangcom.controller.api.todo;

import capstone.sangcom.entity.dto.todoSection.TodoDTO;
import capstone.sangcom.controller.api.response.todo.TodoResponse;
import capstone.sangcom.service.todo.TodoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    }

    /**
     * todo-list 할 일 등록
     */
    @PostMapping("")
    public ResponseEntity<TodoDTO> insertTodoList(){

    }

    /**
     * todo-list 할 일 수정
     */
    @PutMapping("/:list_id")
    public ResponseEntity<TodoDTO> updateTodoList(){

    }

    /**
     * todo-list 할 일 삭제
     */
    @DeleteMapping("/:list_id")
    public ResponseEntity<TodoDTO> deleteTodoList(){

    }

    /**
     * todo-list 할 일 체크
     */
    @GetMapping("/:list_id")
    public ResponseEntity<TodoDTO> checkTodoList(){

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