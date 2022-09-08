package capstone.sangcom.controller.api.meals;

import capstone.sangcom.controller.api.response.board.BoardDetailResponse;
import capstone.sangcom.controller.api.response.meals.MealsResponse;
import capstone.sangcom.entity.dto.mealsSection.MealsInputDTO;
import capstone.sangcom.entity.dto.mealsSection.MealsOutputDTO;
import capstone.sangcom.service.meals.MealsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.CharacterCodingException;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/school")
@RequiredArgsConstructor
public class MealsController {

    private final MealsService mealsService; // mealsService의 객체

//    Neis neis = new Neis;


//    public static void main(String[] args) {
//        String token = "ec9ada510672412d9623900e44882a9f";// 네아로 접근 토큰 값";
//        String header = "Bearer " + token; // Bearer 다음에 공백 추가
//        try {
//            String apiURL = "https://open.neis.go.kr/hub/mealServiceDietInfo";
//            URL url = new URL(apiURL);
//            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("Authorization", header);
//            int responseCode = con.getResponseCode();
//            BufferedReader br;
//            if(responseCode==200) { // 정상 호출
//                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            } else {  // 에러 발생
//                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//            }
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = br.readLine()) != null) {
//                response.append(inputLine);
//            }
//            br.close();
//            System.out.println(response.toString());
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }



    @GetMapping("/cafeteria")
    public MealsOutputDTO getMealsInfo() {// @RequestBody MealsInputDTO mealsInputDTO,
                                                      //@RequestParam String MLSV_FROM_YMD,
                                                      // @RequestParam String MLSV_TO_YMD) {

//        List<MealsOutputDTO> mealsOutPut = mealsService.getMeals(MLSV_FROM_YMD, MLSV_TO_YMD);
//
//        return ResponseEntity
//                .ok(new MealsResponse(true, mealsOutPut));

        return mealsService.getMeals();
    }


//    /**
//     * 급식 받아오기 (시도교육청코드, 표준학교코드, 시작일자, 종료일자에 해당하는 급식)
//     */
//    @GetMapping("/cafeteria") // ?MLSV_FROM_YMD={시작일자}&MLSV_TO_YMD={종료일자}
//    public ResponseEntity<MealsResponse> getMealsInfo(@RequestParam String MLSV_FROM_YMD, // 시작일자 입력
//                                                      @RequestParam String MLSV_TO_YMD) { // 종료일자 입력
//
//        List<MealsOutputDTO> mealsOutPut = mealsService.getMeals(MLSV_FROM_YMD, MLSV_TO_YMD);
//        // 서비스 단의 getMeals 메소드에 "매개변수 mealsInput, MLSV_FROM_YMD, MLSV_TO_YMD 값"에 해당하는 선택적 갯수의 무언가를 반환한다.
//
//        return ResponseEntity
//                .ok(new MealsResponse(true, mealsOutPut)); // 성공 응답 메시지를 반환한다.
//
////        log.info("user:{}")
//
//        // if-else문을 안써도 되지 않나?
////        if (mealsService.getMealsOK()) // mealsService의 getMealsOK() 메소드
////            return ResponseEntity
////                    .ok(new MealsResponse(true, mealsOutPut));
////        else
////            return ResponseEntity
////                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
////                    .body(new MealsResponse(false));
////                    .build(); //.body 오류를 무시하기 위한 코드
//    }

//    /**
//     * 월별 급식 받아오기
//     */


//    /**
//     * RestTemplate으로 외부 Api 받아오기  // 참고: https://hyeonyeee.tistory.com/34
//     */
//    @GetMapping("/cafeteria")
//    public ResponseEntity<MealsResponse> getNeisMealsInfo(// @RequestBody MealsInputDTO mealsInput, // 시도교육청코드, 표준학교코드
//                                                          @RequestParam String MLSV_FROM_YMD, // 시작일자
//                                                          @RequestParam String MLSV_TO_YMD) { // 종료일자
//
////        String url = "http://https://open.neis.go.kr/hub/mealServiceDietInfo?ATPT_OFCDC_SC_CODE=B10&SD_SCHUL_CODE=7010178";
//        // neis-api url // 시도교육청코드 B10 // // 표준학교코드 7010178
//
//        // get parameter 담아주기
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://https://open.neis.go.kr/hub/mealServiceDietInfo?ATPT_OFCDC_SC_CODE=B10&SD_SCHUL_CODE=7010178&MLSV_FROM_YMD={시작일자}&MLSV_TO_YMD={종료일자}")
//                .queryParam("시작일자", MLSV_FROM_YMD)
//                .queryParam("종료일자", MLSV_TO_YMD);
//        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        httpRequestFactory.setConnectTimeout(30000); // 연결시간 초과
//
//        //Rest template setting
//        RestTemplate restTpl = new RestTemplate(httpRequestFactory);
//        HttpHeaders headers  = new HttpHeaders(); // 담아줄 header
//        HttpEntity entity = new HttpEntity<>(headers); // http entity에 header 담아줌
//
//        ResponseEntity<MealsResponse>  responseEntity = restTpl.exchange
//                ("http://https://open.neis.go.kr/hub/mealServiceDietInfo?ATPT_OFCDC_SC_CODE=B10&SD_SCHUL_CODE=7010178",
//                        HttpMethod.GET, entity, MealsResponse.class);
////        L.info("responseEntity.getBody()" + responseEntity.getBody());
////        List result = (List) responseEntity.getBody();
//////
////        return ResponseEntity.ok(result);
//        return ResponseEntity
//                .ok(new MealsResponse(true, responseEntity.getBody().getMealsOutput())); // 불확실
//
//
//
//
////        List<MealsOutputDTO> mealsOutPut = mealsService.getMeals(mealsInput, MLSV_FROM_YMD, MLSV_TO_YMD);
////        // 서비스 단의 getMeals 메소드에 "매개변수 mealsInput, MLSV_FROM_YMD, MLSV_TO_YMD 값"에 해당하는 선택적 갯수의 무언가를 반환한다.
////
////        return ResponseEntity
////                .ok(new MealsResponse(true, mealsOutPut)); // 성공 응답 메시지를 반환한다.
//    }

}