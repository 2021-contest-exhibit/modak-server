package modak.camping.modakdata;

import lombok.RequiredArgsConstructor;
import modak.camping.modakdata.camping.Camping;
import modak.camping.modakdata.camping.CampingFirestoreRepository;
import modak.camping.modakdata.camping.CampingRepository;
import modak.camping.modakdata.dto.CampingSearchCondition;
import modak.camping.modakdata.environment.Environment;
import modak.camping.modakdata.environment.EnvironmentRepository;
import modak.camping.opendata.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
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
    public String saveCampings(List<Base> baseList) {
        baseList.stream()
                .forEach(base -> {
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
                            base.getFacltDivNm()
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
