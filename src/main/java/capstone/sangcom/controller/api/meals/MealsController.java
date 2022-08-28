package capstone.sangcom.controller.api.meals;

import capstone.sangcom.controller.api.response.board.BoardDetailResponse;
import capstone.sangcom.controller.api.response.meals.MealsResponse;
import capstone.sangcom.entity.dto.mealsSection.MealsInputDTO;
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

//    Neis neis = new Neis;

    /**
     * 급식 받아오기 (시도교육청코드, 표준학교코드, 시작일자, 종료일자에 해당하는 급식)
     */
    @GetMapping("/cafeteria?MLSV_FROM_YMD={시작일자}&MLSV_TO_YMD={종료일자}")
    public ResponseEntity<MealsResponse> getMealsInfo(@RequestParam MealsInputDTO mealsInput, // 시도교육청코드, 표준학교코드
                                                      @PathVariable("시작일자") String MLSV_FROM_YMD, // 시작일자 입력
                                                      @PathVariable("종료일자") String MLSV_TO_YMD) { // 종료일자 입력

        List<MealsOutputDTO> mealsOutPut = mealsService.getMeals(mealsInput, MLSV_FROM_YMD, MLSV_TO_YMD);
        // 서비스 단의 getMeals 메소드에 "매개변수 mealsInput, MLSV_FROM_YMD, MLSV_TO_YMD 값"에 해당하는 선택적 갯수의 무언가를 반환한다.

        return ResponseEntity
                .ok(new MealsResponse(true, mealsOutPut)); // 성공 응답 메시지를 반환한다.

        // if-else문을 안써도 되지 않나?
//        if (mealsService.getMealsOK()) // mealsService의 getMealsOK() 메소드
//            return ResponseEntity
//                    .ok(new MealsResponse(true, mealsOutPut));
//        else
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new MealsResponse(false));
//                    .build(); //.body 오류를 무시하기 위한 코드
    }

    /**
     * 월별 급식 받아오기
     */

}