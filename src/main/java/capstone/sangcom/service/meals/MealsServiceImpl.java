package capstone.sangcom.service.meals;

import capstone.sangcom.controller.api.response.meals.MealsResponse;
import capstone.sangcom.entity.dto.mealsSection.MealsInputDTO;
import capstone.sangcom.entity.dto.mealsSection.MealsOutputDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MealsServiceImpl implements MealsService{

//    private final MealsRepository mealsRepository;

    public List<MealsOutputDTO> getMeals(String MLSV_FROM_YMD, String MLSV_TO_YMD) {

        URI uri = UriComponentsBuilder
                .fromHttpUrl("https://open.neis.go.kr/hub/mealServiceDietInfo?ATPT_OFCDC_SC_CODE=B10&SD_SCHUL_CODE=7010178")
//                .path("/v1/search/local.json")
                .queryParam("시작일자", MLSV_FROM_YMD)
                .queryParam("종료일자", MLSV_TO_YMD)
                .encode()
                .build()
                .toUri();

        RequestEntity requestEntity = RequestEntity
                .get(uri)
//                .header("X-Naver-Client-Id","네이버에서 받은 id")
//                .header("X-Naver-Client-Secret","네이버에서 받은 secret key")
                .build();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<MealsResponse> mealsResponse = restTemplate.exchange(requestEntity, MealsResponse.class);
        log.info("uri:{}",uri);
        log.info("searchList:{}",mealsResponse.getBody());
        return mealsResponse.getBody().getMealsOutput();


//        return getMeals(MLSV_FROM_YMD, MLSV_TO_YMD);
    }




//    @Override
//    @Transactional
//    public boolean getMealsOK() {
//        return true;
//    }
}
