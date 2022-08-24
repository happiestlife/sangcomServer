package capstone.sangcom.controller.api.meals;

import capstone.sangcom.controller.api.response.board.BoardDetailResponse;
import capstone.sangcom.controller.api.response.meals.MealsResponse;
import capstone.sangcom.entity.dto.mealsSection.MealsOutputDTO;
import capstone.sangcom.service.meals.MealsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/school")
@RequiredArgsConstructor
public class MealsController {

    private final MealsService mealsService; // mealsService의 객체

    /**
     * 급식 받아오기
     */
    @GetMapping("/cafeteria?MLSV_FROM_YMD={시작일자}&MLSV_TO_YMD={종료일자}")
    public ResponseEntity<MealsResponse> getMealsInfo(@PathVariable("시작일자") String MLSV_FROM_YMD,
                                                      @PathVariable("종료일자") String MLSV_TO_YMD) {
        // @PathVariable: url 경로({시작일자}, {종료일자})를 변수화하여 사용할 수 있도록 해준다.

        List<MealsOutputDTO> mealsResponse = mealsService.getMeals(MLSV_FROM_YMD, MLSV_TO_YMD);
        // 서비스 단의 getMeals 메소드에 (MLSV_FROM_YMD, MLSV_TO_YMD)에 해당하는 선택적 갯수의 무언가를 반환한다.
        // List의 generic은 MealsOutputDTO


        return ResponseEntity
                .ok(new MealsResponse(true, ));

        // if-else문을 왜 써야하지?
//        if (mealsService.getMealsOK()) // mealsService의 getMealsOK() 메소드
//            return ResponseEntity
//                    .ok(new MealsResponse(true, mealsResponse));
//        else
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
////                    .body(new MealsResponse(false), );
//                    .build(); //.body 오류를 무시하기 위한 코드
    }

    /**
     * 월별 급식 받아오기
     */

}
