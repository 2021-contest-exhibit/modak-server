package modak.camping.modakdata.camping;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import modak.camping.modakdata.dto.condition.CampingSearchCondition;
import modak.camping.modakdata.dto.request.FindCampingsRequestDto;
import modak.camping.modakdata.environment.QEnvironment;
import modak.camping.modakdata.facility.QFacility;
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
import static modak.camping.modakdata.good.QGood.*;

public class CampingRepositoryCustomImpl implements CampingRepositoryCustom {

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
            environmentEq(condition.getEnvironmentName(), environmentSub),
            contentIdEq(condition.getContentId()),
            nameContains(condition.getNameContains())
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(camping.contentId.asc())
        .fetchResults();

    return new PageImpl<>(results.getResults(), pageable, results.getTotal());
  }

  @Override
  public Page<Camping> findAll(FindCampingsRequestDto findCampingsRequestDto, Pageable pageable) {
    QEnvironment environmentSub = new QEnvironment("environmentSub");
    QFacility facilitySub = new QFacility("facilitySub");

    QueryResults<Camping> results = queryFactory.selectFrom(camping)
        .where(
            operationTypeEqList(findCampingsRequestDto.getOperationTypeEqual()),
            regionContainsList(findCampingsRequestDto.getRegionContains()),
            environmentEqList(findCampingsRequestDto.getEnvironmentEqual(), environmentSub),
            facilityEqual(findCampingsRequestDto.getFacilityEqual(), facilitySub),
            nameContains(findCampingsRequestDto.getNameContains())
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(camping.contentId.asc())
        .fetchResults();

    return new PageImpl<>(results.getResults(), pageable, results.getTotal());
  }

  @Override
  public Page<Camping> findAllToday(Pageable pageable) {
    QueryResults<Camping> results = queryFactory
        .select(camping)
        .from(camping)
        .leftJoin(camping.goods, good)
        .groupBy(camping.contentId)
        .orderBy(good.camping.count().desc(), camping.contentId.asc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetchResults();

    return new PageImpl<>(results.getResults(), pageable, results.getTotal());
  }

  private BooleanExpression environmentEqList(List<String> environmentEqualList,
      QEnvironment environmentSub) {
    BooleanExpression res = null;

    for (String environment : environmentEqualList) {
      res = res == null ? environmentEq(environment, environmentSub)
          : res.or(environmentEq(environment, environmentSub));
    }

    return res;
  }

  private BooleanExpression operationTypeEqList(List<String> operationTypeEqualList) {
    BooleanExpression res = null;

    for (String operationTypeEqual : operationTypeEqualList) {
      res = res == null ? operationTypeEq(operationTypeEqual)
          : res.or(operationTypeEq(operationTypeEqual));
    }

    return res;
  }

  private BooleanExpression regionContainsList(List<String> regionContainsList) {
    BooleanExpression res = null;

    for (String regionContains : regionContainsList) {
      res = res == null ? regionContains(regionContains) : res.or(regionContains(regionContains));
    }

    return res;
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

  private BooleanExpression environmentEq(String environmentName, QEnvironment environmentSub) {
    return StringUtils.isEmpty(environmentName) ? null :
        camping.contentId.in(
            JPAExpressions.select(environmentSub.camping.contentId)
                .from(environmentSub)
                .where(environmentSub.name.eq(environmentName))
        );
  }

  private BooleanExpression facilityEqual(List<String> facilityNameList, QFacility facilitySub) {
    return facilityNameList.size() == 0 ? null:
        camping.contentId.in(
            JPAExpressions.select(facilitySub.camping.contentId)
              .from(facilitySub)
              .where(facilitySub.name.in(facilityNameList))
              .groupBy(facilitySub.camping.contentId)
              .having(facilitySub.camping.contentId.count().eq( Long.valueOf(facilityNameList.size()) ))
        );
  }
}
