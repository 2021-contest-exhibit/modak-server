package modak.camping.opendata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Service
@Data
public class BaseServiceImpl implements BaseService{
    private final BaseRepository baseRepository;

    public void getOpenDataJson(int rangePageNo, int numOfRows) throws Exception {
        for(int pageNo=1; pageNo<= rangePageNo; pageNo++) {
            String openData = getOpenData(pageNo, numOfRows);
            List<Map<String, Object>> mapList = xmlToListMap(openData);
            saveBases(mapList);
            System.out.println("진행상황" + pageNo);
        }
    }
    private void saveBases(List<Map<String, Object>> mapList) {

        mapList.forEach(map-> {

            ObjectMapper objectMapper = new ObjectMapper();
            Base base = objectMapper.convertValue(map, Base.class);
            baseRepository.save(base);

        });
    }

    private String getOpenData(int pageNo, int numOfRows) throws Exception {

        StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/GoCamping/basedList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=m0tgvXV2adR1HjHBAzM0VCqTBT%2FlVCphcEKiQ%2BJVon%2FneZXUuMfpFlckFIuk%2BIIH4es%2F9qQJ354zBjsslknjZw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("m0tgvXV2adR1HjHBAzM0VCqTBT/lVCphcEKiQ+JVon/neZXUuMfpFlckFIuk+IIH4es/9qQJ354zBjsslknjZw==", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(Integer.toString(pageNo), "UTF-8")); /*현재 페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(Integer.toString(numOfRows), "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS(아이폰),AND(안드로이드),WIN(윈도우폰),ETC*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("camping", "UTF-8")); /*서비스명=어플명*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        return sb.toString();
    }

    private List<Map<String, Object>> xmlToListMap(String openData) throws JsonProcessingException {
        JSONObject jObject = XML.toJSONObject(openData);
        ObjectMapper mapper = new ObjectMapper();


        List<Map<String, Object>> mapList = (List<Map<String, Object>>)
                ((Map<String, Object>)
                        ((Map<String, Object>)
                                ((Map<String, Object>)
                                        mapper.readValue(jObject.toString(), Map.class)
                                                .get("response")
                                ).get("body")
                        ).get("items")
                ).get("item");

        return mapList;
    }
}
