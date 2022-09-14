package capstone.sangcom.service.meal;

import capstone.sangcom.entity.dto.mealSection.GetMealDTO;
import capstone.sangcom.entity.dto.mealSection.MealDTO;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.apache.tomcat.util.json.ParseException;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MealServiceImpl implements MealService{

    @Value("${meal.ATPT_OFCDC_SC_CODE}")
    private String ATPT_OFCDC_SC_CODE;

    @Value("${meal.SD_SCHUL_CODE}")
    private String SD_SCHUL_CODE;

    @Value("${meal.key}")
    private String key;

    private final String URL;

    private final RestTemplate restTemplate;

    @Autowired
    public MealServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.URL = "https://open.neis.go.kr/hub/mealServiceDietInfo";
    }

    @Override
    public List<MealDTO> getMeals(String from, String to) throws ParseException, org.json.simple.parser.ParseException, java.text.ParseException {
        int size = Integer.parseInt(to) - Integer.parseInt(from);
        if(size == 0)
            size = 1;
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("Key", key)
                .queryParam("pSize", size)
                .queryParam("ATPT_OFCDC_SC_CODE", ATPT_OFCDC_SC_CODE)
                .queryParam("SD_SCHUL_CODE", SD_SCHUL_CODE)
                .queryParam("MLSV_FROM_YMD", from)
                .queryParam("MLSV_TO_YMD", to).build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> apiResult = restTemplate.exchange(uri.toString(), HttpMethod.GET, httpEntity, String.class);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(apiResult.getBody());
        JSONArray result = (JSONArray)((JSONObject)((JSONArray) jsonObject.get("mealServiceDietInfo")).get(1)).get("row");

        ArrayList<MealDTO> rs = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            JSONObject o = (JSONObject)result.get(i);

            String ddish_nm = (String)o.get("DDISH_NM");
            String[] dd_arr = Jsoup.parse(ddish_nm).text().split("[\\s*]");
            ArrayList<String> dd_result = new ArrayList<>();
            for (int j = 0; j < dd_arr.length; j++) {
                if(dd_arr[j].equals("") || dd_arr[j].charAt(0) == '(')
                    continue;
                dd_result.add(dd_arr[j]);
            }

            String cal = (String)o.get("CAL_INFO");

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Calendar calendar = Calendar.getInstance();
            Date date = format.parse((String) o.get("MLSV_YMD"));
            calendar.setTime(date);

            rs.add(new MealDTO(String.valueOf(calendar.get(Calendar.YEAR)), String.valueOf(calendar.get(Calendar.MONTH) + 1), String.valueOf(calendar.get(Calendar.DATE)),
                    dd_result, new StringTokenizer(cal).nextToken()));
        }

        return rs;
    }
}
