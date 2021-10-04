package modak.camping.modakdata.camping;

import java.util.List;
import java.util.Set;

public interface CampingRepositoryCustom {
    List<String> findCampingAddr();
    Set<String> findCampingOperationType();
}
