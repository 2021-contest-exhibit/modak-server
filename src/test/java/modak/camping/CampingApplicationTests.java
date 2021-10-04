package modak.camping;

import com.querydsl.jpa.impl.JPAQueryFactory;
import modak.camping.modakdata.camping.Camping;
import modak.camping.modakdata.QCamping;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class CampingApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		Camping camping = new Camping();
		camping.setContentId("2");

		em.persist(camping);

		JPAQueryFactory query = new JPAQueryFactory(em);
		QCamping qCamping = QCamping.camping;

		Camping result = query
				.selectFrom(qCamping)
				.limit(1)
				.fetchOne();

		System.out.println("result = " + result);
	}

}
