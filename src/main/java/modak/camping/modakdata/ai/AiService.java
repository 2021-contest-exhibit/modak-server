package modak.camping.modakdata.ai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import modak.camping.modakdata.camping.Camping;
import modak.camping.modakdata.camping.CampingRepository;
import modak.camping.modakdata.good.Good;
import modak.camping.modakdata.good.GoodRepository;
import modak.camping.modakdata.user.User;
import modak.camping.modakdata.user.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AiService {

  private final GoodRepository goodRepository;
  private final UserRepository userRepository;
  private final CampingRepository campingRepository;
  private ObjectMapper mapper = new ObjectMapper();

  public Page<Camping> getRecommendCamping(String email, Pageable pageable)
      throws JsonProcessingException {

    Optional<User> userOptional = userRepository.findByEmail(email);

    if(!userOptional.isPresent()) {
      userOptional = userRepository.findByEmail("nmeiX1kkZsNGR6BNg7RCudIAAWk2");
    }

    User user = userOptional.get();

    List<Good> goodList = goodRepository.findByUser(user);

    List<Long> contentIdList = goodList.stream()
        .filter(good -> StringUtils.isNotEmpty(good.getCamping().getLongDescription()))
        .map(good -> good.getCamping().getContentId())
        .collect(Collectors.toList());

    if(contentIdList.size() == 0) contentIdList.add(4L); // 좋아요한 데이터가 없거나, 적절한 좋아요 데이터가 없다면 강제로 4번 캠핑으로 추천

    Map<String, Object> recommendHashMap = mapper.readValue(getRecommendCamping(contentIdList), Map.class);

    List<Long> recommendContentList = ((List<Integer>) recommendHashMap.get("data")).stream()
        .map(integer -> Long.valueOf(integer))
        .collect(Collectors.toList());

    List<Camping> recommendCampingList = recommendContentList.stream()
        .map(recommendContent -> campingRepository.findById(recommendContent).get())
        .collect(Collectors.toList());

    return new PageImpl<>(recommendCampingList, pageable, recommendCampingList.size());
  }

  private String getRecommendCamping(List<Long> contentIdList) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
    headers.add("Accept", MediaType.APPLICATION_JSON.toString());
    String url = "http://localhost:8000/ai/recommend";

    RestTemplate restTemplate = new RestTemplate();

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("contentIdList", contentIdList);

    HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(),headers);
    return restTemplate.postForObject(url, entity, String.class);
  }
}
