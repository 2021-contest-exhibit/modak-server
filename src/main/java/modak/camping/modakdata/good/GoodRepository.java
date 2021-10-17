package modak.camping.modakdata.good;

import modak.camping.modakdata.camping.Camping;
import modak.camping.modakdata.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface GoodRepository extends JpaRepository<Good, Long> {

    Optional<Good> findByUserAndCamping(User user, Camping camping);

    @Transactional
    Long deleteByUserAndCamping(User user,Camping camping);
}
