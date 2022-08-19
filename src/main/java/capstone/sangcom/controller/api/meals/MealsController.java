package capstone.sangcom.controller.api.meals;

import capstone.sangcom.controller.api.response.meals.MealsResponse;
import capstone.sangcom.entity.dto.mealsSection.MealsResponseDTO;
import capstone.sangcom.service.schoolMeals.MealsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/school")
@RequiredArgsConstructor
public class MealsController {

    private final MealsService mealsService;

    /**
     * 급식 받아오기
     */
    @GetMapping("/cafeteria?MLSV_FROM_YMD={시작일자}&MLSV_TO_YMD={종료일자}")
    public ResponseEntity<MealsResponse> getMealsInfo(@RequestParam String MLSV_FROM_YMD,
                                                      @RequestParam String MLSV_TO_YMD) {

        List<MealsResponseDTO> mealsResponse = mealsService.getMeals(MLSV_FROM_YMD, MLSV_TO_YMD);

        if (mealsService.getMealsOK())
            return ResponseEntity
                    .ok(new MealsResponse(true, mealsResponse));
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new MealsResponse(false), );
                    .build();
    }

//    @GetMapping("/cafeteria?MLSV_FROM_YMD={시작일자}&MLSV_TO_YMD={종료일자}")
//    public MealsResponseDTO get(@RequestParam String MLSV_FROM_YMD,
//                                @RequestParam String MLSV_TO_YMD) {
//        if (mealsService.getMeals())
//            return ResponseEntity
//                    .ok(new MealsResponse(true));
//        else
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new MealsResponse(false));
//    }
}
