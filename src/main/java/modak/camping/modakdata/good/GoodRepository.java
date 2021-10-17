package modak.camping.modakdata.good;

import modak.camping.modakdata.camping.Camping;
import modak.camping.modakdata.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface GoodRepository extends JpaRepository<Good, Long> {

    @Transactional
    Long deleteByUserAndCamping(User user,Camping camping);
}
