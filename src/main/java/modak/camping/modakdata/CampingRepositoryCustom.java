package modak.camping.modakdata;

import java.util.List;
import java.util.Set;

public interface CampingRepositoryCustom {
    List<String> findCampingAddr();
    Set<String> findCampingOperationType();
}
