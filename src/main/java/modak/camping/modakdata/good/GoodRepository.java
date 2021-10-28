package modak.camping.modakdata.good;

import java.util.List;
import modak.camping.modakdata.camping.Camping;
import modak.camping.modakdata.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface GoodRepository extends JpaRepository<Good, Long> {

    Optional<Good> findByUserAndCamping(User user, Camping camping);
    List<Good> findByUser(User user);

    @Transactional
    Long deleteByUserAndCamping(User user,Camping camping);
}
