package modak.camping.modakdata.user;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import modak.camping.modakdata.camping.QCamping;
import modak.camping.modakdata.good.QGood;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import javax.persistence.EntityManager;

import static modak.camping.modakdata.camping.QCamping.*;
import static modak.camping.modakdata.good.QGood.*;
import static modak.camping.modakdata.user.QUser.*;

public class UserRepositoryCustomImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public UserRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<User> findAll(String email) {
        QueryResults<User> results = queryFactory
                .selectFrom(user)
                .distinct()
                .join(user.goods, good).fetchJoin()
                .join(good.camping, camping).fetchJoin()
                .where(emailEq(email))
                .fetchResults();

        return new PageImpl<>(results.getResults());
    }

    private BooleanExpression emailEq(String email) {
        return StringUtils.isEmpty(email) ? null : user.email.eq(email);
    }
}
