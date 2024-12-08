package dbServices.repository;

import dbServices.model.FunctionEntity;
import dbServices.model.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<PointEntity, Long> {
    List<PointEntity> findByFunction(FunctionEntity function);
}
