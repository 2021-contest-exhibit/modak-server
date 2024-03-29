package modak.camping.modakdata.camping;

import modak.camping.modakdata.dto.condition.CampingSearchCondition;
import modak.camping.modakdata.dto.request.FindCampingsRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface CampingRepositoryCustom {
    List<String> findCampingAddr();
    Set<String> findCampingOperationType();
    Page<Camping> findAll(CampingSearchCondition condition, Pageable pageable);
    Page<Camping> findAll(FindCampingsRequestDto findCampingsRequestDto, Pageable pageable);
    Page<Camping> findAllToday(Pageable pageable);
}
