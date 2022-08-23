package capstone.sangcom.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
 * movie search API
 *
 */

@Controller
public class SearchController_example {
    @RequestMapping(value = "/movie/search", produces = "application/json")
    public void movieSearch(
            @RequestParam(value = "search", required = false)
                    String search,
            @RequestParam(value = "api_key", required = false)
                    String api_key,
            HttpServletResponse response)
            throws Exception {
        //결과를 담을 변수들
        StringBuffer result = new StringBuffer();
        String strResult = "";
        try {
            // URL 설정
            StringBuilder urlBuilder = new StringBuilder("http://www.smileplace_movie/movie/search");

            // search 변수는 인코딩이 필요하다고 했으므로 그 부분만 인코딩
            urlBuilder.append("?search=" + URLEncoder.encode(search, "UTF-8"));
            urlBuilder.append("&api_key=" + api_key);
            urlBuilder.append("&type=json");

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Request 형식 설정
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            // 응답 데이터 받아오기
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 & conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            conn.disconnect();
            strResult = result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Response 형식 설정 -> JSON으로 데이터 보내기
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(strResult);
    }
}
