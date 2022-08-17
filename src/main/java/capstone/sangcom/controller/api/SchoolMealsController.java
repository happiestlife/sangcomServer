package capstone.sangcom.controller.api;

//import capstone.sangcom.dto.schoolMeals.School;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController // 해당 Class는 REST API를 처리하는 Controller
@RequestMapping("/api") // URL을 지정해주는 Annotation
public class SchoolMealsController {

    @GetMapping("/schoolmenu.ml/api/{4}/{7010178}") // https://schoolmenukr.ml/api/[학교유형]/[학교코드]
    public String schoolMeals() {
        return "상명대학교 사범대학 부속 여자고등학교 이번 달의 식단입니다.";
    }
}


//        https://open.neis.go.kr/hub/mealServiceDietInfo?
//        KEY=ec9ada510672412d9623900e44882a9f&Type=json&pIndex=1&pSize=10
//
//        try {
//        School school = School.Companion.find(School.Region.SEOUL, "선덕고등학교");
//
//        List<Menu> menu = school.getMonthlyMenu(2019, 1);
//        List<Schedule> schedule = school.getMonthlySchedule(2018, 12);
//
//        // 2019년 1월 2일 점심 급식 식단표
//        System.out.println(menu.get(1).getLunch());
//
//        // 2018년 12월 5일 학사일정
//        System.out.println(schedule.get(4));
//
//    } catch (NEISException e) {
//        e.printStackTrace();



