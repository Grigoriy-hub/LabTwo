package dbServices.repository;


import dbServices.model.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PointRepository extends JpaRepository<PointEntity, Long> {
    Optional<PointEntity> findById(Long id);

}
