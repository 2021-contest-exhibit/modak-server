package modak.camping.modakdata;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static modak.camping.modakdata.QCamping.*;

public class CampingRepositoryCustomImpl implements CampingRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public CampingRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<String> findCampingAddr() {
        return queryFactory
                .select(camping.addr)
                .from(camping)
                .fetch();
    }

    @Override
    public Set<String> findCampingOperionaType() {
        return queryFactory
                .select(camping.operationType)
                .from(camping)
                .groupBy(camping.operationType)
                .fetch()
                .stream()
                .filter(operationType -> StringUtils.isNotEmpty(operationType))
                .collect(Collectors.toSet());
    }
}
