package modak.camping.modakdata.environment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvironmentRepository extends JpaRepository<Environment,Long>,EnvironmentRepositoryCustom {
}
