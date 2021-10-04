package modak.camping.modakdata;

import modak.camping.modakdata.camping.Camping;
import modak.camping.modakdata.dto.CampingSearchCondition;
import modak.camping.opendata.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface ModakdataService {
    String saveCamping(Camping camping) throws Exception;
    String saveCampings(List<Base> baseList);
    Set<String> findCampingAddr();
    Set<String> findCampingOperationType();
    Set<String> findEnvironmentName();
    Page<Camping> findAllCampingPage(CampingSearchCondition campingSearchCondition, Pageable pageable);
}
