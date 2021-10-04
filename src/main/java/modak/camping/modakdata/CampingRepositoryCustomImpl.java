package modak.camping.modakdata;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

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
}
