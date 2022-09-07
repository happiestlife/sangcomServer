package capstone.sangcom.service.meals;

import capstone.sangcom.controller.api.response.meals.MealsResponse;
import capstone.sangcom.entity.dto.mealsSection.MealsOutputDTO;
import capstone.sangcom.repository.meals.MealsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MealsServiceImpl implements MealsService{

    private final MealsRepository mealsRepository;

    @Override
    @Transactional
    public List<MealsOutputDTO> getMeals(String MLSV_FROM_YMD, String MLSV_TO_YMD) {
//        return mealsRepository.getMealsInfo(MLSV_FROM_YMD, MLSV_TO_YMD);

        //URI를 빌드한다.
        URI uri = UriComponentsBuilder
                .fromUriString("http://open.neis.go.kr")
                .path("/hub/mealServiceDietInfo?ATPT_OFCDC_SC_CODE=B10&SD_SCHUL_CODE=7010178")
                .queryParam("MLSV_FROM_YMD",MLSV_FROM_YMD)
                .queryParam("MLSV_TO_YMD", MLSV_TO_YMD)
                .encode()
                .build()
                .toUri();

        RequestEntity requestEntity = RequestEntity
                .get(uri)
                .build();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<MealsResponse> result = restTemplate.exchange(requestEntity, MealsResponse.class);
        log.info("uri:{}", uri);
        log.info("mealInfo:{}", result.getBody());

        return (List<MealsOutputDTO>) result.getBody();
    }

//    @Override
//    @Transactional
//    public boolean getMealsOK() {
//        return true;
//    }
}
