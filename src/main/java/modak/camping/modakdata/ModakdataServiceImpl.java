package modak.camping.modakdata;

import lombok.RequiredArgsConstructor;
import modak.camping.modakdata.camping.*;
import modak.camping.modakdata.dto.CampingSearchCondition;
import modak.camping.modakdata.environment.Environment;
import modak.camping.modakdata.environment.EnvironmentRepository;
import modak.camping.opendata.Base;
import modak.camping.opendata.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModakdataServiceImpl implements ModakdataService {
    private final CampingRepository campingRepository;
    private final CampingFirestoreRepository campingFirestoreRepository;
    private final EnvironmentRepository environmentRepository;

    @Override
    public String saveCamping(Camping camping) throws Exception {
//        campingFirestoreRepository.save(camping);
        campingRepository.save(camping);

        return "ok";
    }

    @Override
    public String saveCampings(List<Base> baseList, Map<Long, List<Image>> imageMap) {
        baseList.stream()
                .forEach(base -> {

                    List<Image> imageList = imageMap.get(base.getContentId());
                    List<CampingImage> campingImageList;

                    if(imageList == null ) {
                        campingImageList = new ArrayList<>();
                    } else {
                        List<Map<String, Object>> jsonDataToListMap = imageMap.get(base.getContentId()).get(0).getJsonDataToListMap();
                        campingImageList = listMapToCampingImageList(jsonDataToListMap);
                    }

                    Camping camping = new Camping(
                            base.getContentId(),
                            base.getFacltNm(),
                            0L,
                            base.getAddr1() + "\t" + base.getAddr2(),
                            base.getTel(),
                            base.getInduty(),
                            base.getOperPdCl(),
                            base.getOperDeCl(),
                            base.getResveCl(),
                            base.getPosblFcltyCl(),
                            base.getSbrsCl(),
                            base.getMapX(),
                            base.getMapY(),
                            base.getFacltDivNm(),
                            base.getLineIntro(),
                            base.getIntro(),
                            campingImageList
                    );


                    try {
                        saveCamping(camping);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if(!base.getLctCl().contains(",")) return;

                    Arrays.stream(base.getLctCl().split(",")).forEach(ev -> {

                        Environment environment = new Environment(ev, camping);
                        environmentRepository.save(environment);
                    });

                });



        return "ok";
    }

    private List<CampingImage> listMapToCampingImageList(List<Map<String,Object>> listMap) {

        List<CampingImage> campingImageList = listMap.stream()
                .map(hashMap -> new CampingImage(
                        String.valueOf( hashMap.get("serialnum")) ,
                        String.valueOf( hashMap.get("modifiedtime") ),
                        String.valueOf( hashMap.get("imageUrl"))
                        )
                )
                .collect(Collectors.toList());

        return campingImageList;
    }

    @Override
    public Set<String> findCampingAddr() {
        List<String> campingAddrList = campingRepository.findCampingAddr();

        Set<String> campingAddrSet = campingAddrList
                .stream()
                .map(addr -> addr.split(" ")[0])
                .filter(addr -> addr.length() < 10) // 이상하게 들어가 있는 데이터 제거
                .collect(Collectors.toSet());

        return campingAddrSet;
    }

    @Override
    public Set<String> findCampingOperationType() {
        return campingRepository.findCampingOperationType();
    }

    @Override
    public Set<String> findEnvironmentName() {
        return environmentRepository.findName();
    }

    @Override
    public Page<Camping> findAllCampingPage(CampingSearchCondition campingSearchCondition, Pageable pageable) {
        return campingRepository.findAll(campingSearchCondition, pageable);
    }
}
