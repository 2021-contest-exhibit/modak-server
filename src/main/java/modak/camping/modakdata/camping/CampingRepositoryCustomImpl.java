package modak.camping.modakdata.camping;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import modak.camping.modakdata.dto.CampingSearchCondition;
import modak.camping.modakdata.environment.QEnvironment;
import modak.camping.modakdata.good.QGood;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static modak.camping.modakdata.camping.QCamping.*;
import static modak.camping.modakdata.environment.QEnvironment.*;
import static modak.camping.modakdata.good.QGood.*;

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
    public Set<String> findCampingOperationType() {
        return queryFactory
                .select(camping.operationType)
                .from(camping)
                .groupBy(camping.operationType)
                .fetch()
                .stream()
                .filter(operationType -> StringUtils.isNotEmpty(operationType))
                .collect(Collectors.toSet());
    }

    @Override
    public Page<Camping> findAll(CampingSearchCondition condition, Pageable pageable) {
        QEnvironment environmentSub = new QEnvironment("environmentSub");

        QueryResults<Camping> results = queryFactory
                .selectFrom(camping)
                .where(
                        regionContains(condition.getRegionContains()),
                        operationTypeEq(condition.getOperationType()),
                        StringUtils.isEmpty(condition.getEnvironmentName()) ? null:
                        camping.contentId.in(
                                JPAExpressions.select(environmentSub.camping.contentId)
                                .from(environmentSub)
                                .where(StringUtils.isEmpty(condition.getEnvironmentName()) ? null : environmentSub.name.eq(condition.getEnvironmentName() )  )
                        ),
                        contentIdEq(condition.getContentId()),
                        nameContains(condition.getNameContains())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(camping.contentId.asc())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
    private BooleanExpression regionContains(String region) {
        return StringUtils.isEmpty(region) ? null : camping.addr.contains(region);
    }
    private BooleanExpression operationTypeEq(String operationType) {
        return StringUtils.isEmpty(operationType) ? null : camping.operationType.eq(operationType);
    }
    private BooleanExpression contentIdEq(Long contentId) {
        return contentId == -1L ? null : camping.contentId.eq(contentId);
    }
    private BooleanExpression nameContains(String name) {
        return StringUtils.isEmpty(name) ? null : camping.name.contains(name);
    }
//    private JPAExpressions environmentNameEq(String environmentName) {
//        return StringUtils.isEmpty(environmentName) ? null : environment.name.eq(environmentName);
//    }
}
