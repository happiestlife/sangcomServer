package capstone.sangcom.controller.api.meals;

import capstone.sangcom.controller.api.response.meals.MealsResponse;
import capstone.sangcom.entity.dto.mealsSection.MealsInputDTO;
import capstone.sangcom.entity.dto.mealsSection.MealsOutputDTO;
import capstone.sangcom.service.meals.MealsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@Slf4j
@RestController
@RequestMapping("/api/school")
//@RequiredArgsConstructor
public class MealsController {

    private final MealsService mealsService; // mealsService의 객체

    public MealsController(MealsService mealsService) {
        this.mealsService = mealsService;
    }

    @GetMapping("/cafeteria")
    public ResponseEntity<MealsResponse> getMealsInfo(@RequestParam String MLSV_FROM_YMD,
                                                      @RequestParam String MLSV_TO_YMD) {

        List<MealsOutputDTO> mealsOutPut = mealsService.getMeals(MLSV_FROM_YMD, MLSV_TO_YMD);

        return ResponseEntity
                .ok(new MealsResponse(mealsOutPut));

    }





//    @GetMapping("/cafeteria")
//    public HashMap<String, Object> callAPI(){
//
//        // 0. 결과값을 담을 객체를 생성합니다.
//        HashMap<String, Object> resultMap = new HashMap<String, Object>();
//        try {
//            // 1. 타임아웃 설정시 HttpComponentsClientHttpRequestFactory 객체를 생성합니다.
//            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//            factory.setConnectTimeout(5000); // 타임아웃 설정 5초
//            factory.setReadTimeout(5000); // 타임아웃 설정 5초
//
//            //Apache HttpComponents : 각 호스트(IP와 Port의 조합)당 커넥션 풀에 생성가능한 커넥션 수
////            HttpClient HttpClient httpClient = HttpClientBuilder.create()
////                    .setMaxConnTotal(50)//최대 커넥션 수
////                    .setMaxConnPerRoute(20).build();
////            factory.setHttpClient(httpClient);
//
//            // 2. RestTemplate 객체를 생성합니다.
//            RestTemplate restTemplate = new RestTemplate(factory);
//
//            // 3. header 설정을 위해 HttpHeader 클래스를 생성한 후 HttpEntity 객체에 넣어줍니다.
////            HttpHeader header = new HttpHeader();
////            HttpEntity<String> entity = new HttpEntity<String>(header);
//
//            // 4. 요청 URL을 정의해줍니다.
//            String url = "https://open.neis.go.kr/hub/mealServiceDietInfo?ATPT_OFCDC_SC_CODE&SD_SCHUL_CODE";
//            UriComponents uri = UriComponentsBuilder
//                    .fromHttpUrl(url)
//                    .queryParam("ATPT_OFCDC_SC_CODE", "B10")
//                    .queryParam("SD_SCHUL_CODE","7010178")
//                    .build(false);
//
//            // 5. exchange() 메소드로 api를 호출합니다.
//            ResponseEntity<Map> response = restTemplate.exchange(uri.toString, HttpMethod.GET, entity, Map.class);
//
//            // 6. 요청한 결과를 HashMap에 추가합니다.
//            // HTTP Status Code
//            resultMap.put("statusCode", response.getStatusCodeValue());
//            // 헤더 정보
//            resultMap.put("header", response.getHeaders());
//            // 반환받은 실제 데이터 정보
//            resultMap.put("body", response.getBody());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }// end catch
//    }//callAPI


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