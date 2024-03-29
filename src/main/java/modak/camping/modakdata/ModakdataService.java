package modak.camping.modakdata;

import modak.camping.modakdata.camping.Camping;
import modak.camping.modakdata.dto.condition.CampingSearchCondition;
import modak.camping.modakdata.dto.request.FindCampingsRequestDto;
import modak.camping.opendata.Base;
import modak.camping.opendata.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ModakdataService {
    String saveCamping(Camping camping) throws Exception;
    String saveCampings(List<Base> baseList, Map<Long, List<Image>> imageMap);
    Set<String> findCampingAddr();
    Set<String> findCampingOperationType();
    Set<String> findEnvironmentName();
    Set<String> findFacilityName();
    Page<Camping> findAllCampingPage(CampingSearchCondition campingSearchCondition, Pageable pageable);
    Page<Camping> findAllCampingPage(FindCampingsRequestDto findCampingsRequestDto, Pageable pageable);
    Page<Camping> findAllCampingTodayPage(Pageable pageable);
}
