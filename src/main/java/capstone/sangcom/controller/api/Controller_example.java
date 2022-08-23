package capstone.sangcom.controller.api;

import org.springframework.web.bind.annotation.*;

@RestController // 해당 Class는 REST API를 처리하는 Controller
@RequestMapping("/api") // RequestMapping은 URL을 지정해주는 Annotation
public class Controller_example {

    @GetMapping("/hello") // http://localhost:8080/api/hello
    public String hello(){
        return "hello spring boot!";
    }

}