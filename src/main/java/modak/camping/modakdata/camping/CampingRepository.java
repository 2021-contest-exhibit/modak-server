package modak.camping.modakdata.camping;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CampingRepository extends JpaRepository<Camping, Long>, CampingRepositoryCustom {
}
