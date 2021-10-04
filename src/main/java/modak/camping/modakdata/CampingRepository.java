package modak.camping.modakdata;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampingRepository extends JpaRepository<Camping, String>, CampingRepositoryCustom {
}
