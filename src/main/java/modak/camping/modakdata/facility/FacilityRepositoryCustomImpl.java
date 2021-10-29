package modak.camping.modakdata.facility;

import static modak.camping.modakdata.facility.QFacility.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

public class FacilityRepositoryCustomImpl implements FacilityRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  public FacilityRepositoryCustomImpl(EntityManager em) {
    this.queryFactory = new JPAQueryFactory(em);
  }


  @Override
  public Set<String> findName() {
    return queryFactory
        .select(facility.name)
        .from(facility)
        .groupBy(facility.name)
        .fetch()
        .stream()
        .collect(Collectors.toSet());
  }
}
