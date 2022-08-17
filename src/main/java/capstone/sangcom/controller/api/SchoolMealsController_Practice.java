package capstone.sangcom.controller.api;

import capstone.sangcom.entity.dto.schoolMeals.SchoolMealsRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SchoolMealsController_Practice {


    /**
     *  민욱이 형이 짜 본 schoolmeals 코드
     */

//    @GetMapping("/schoolmeals")
//    public ResponseEntity<String> requestGet(@RequestParam String url) {
//        return ResponseEntity.ok().body(url);
//    }

    @GetMapping("/schoolmeals")
    public String schoolMeals(SchoolMealsRequest userRequest) {
        return userRequest.toString();
    }
}

    /**
     *  파이썬 급식 코드
     */

//import requests;
//import json;
//
//class SchoolMealsController:
//
//        params = {
//        "KEY": "6707742900d647b098d46e32a9926c9a",
//        "Type": "json",
//        }
//
//        schoolinfo = {}
//
//        base_url = "https://open.neis.go.kr/hub/"