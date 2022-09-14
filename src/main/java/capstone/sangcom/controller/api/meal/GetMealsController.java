package capstone.sangcom.controller.api.meal;

import capstone.sangcom.controller.api.response.meal.GetMealResponse;
import capstone.sangcom.entity.dto.mealSection.MealDTO;
import capstone.sangcom.service.meal.MealService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GetMealsController {

    private final MealService mealService;

    @GetMapping("/api/school/cafeteria")
    public ResponseEntity<GetMealResponse> getMeals(@RequestParam("MLSV_FROM_YMD") String from,
                                                    @RequestParam("MLSV_TO_YMD") String to) throws java.text.ParseException, ParseException, org.json.simple.parser.ParseException {
        List<MealDTO> meals = mealService.getMeals(from, to);

        return ResponseEntity
                .ok(new GetMealResponse(true, meals));
    }
}
