package modak.camping.modakdata.environment;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.Set;
import java.util.stream.Collectors;

import static modak.camping.modakdata.environment.QEnvironment.*;

public class EnvironmentRepositoryCustomImpl implements EnvironmentRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public EnvironmentRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Set<String> findName() {
        return queryFactory
                .select(environment.name)
                .from(environment)
                .groupBy(environment.name)
                .fetch()
                .stream()
                .collect(Collectors.toSet());
    }
}
