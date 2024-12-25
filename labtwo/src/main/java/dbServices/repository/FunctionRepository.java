package dbServices.repository;

import dbServices.model.FunctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface FunctionRepository extends JpaRepository<FunctionEntity, Long> {
    List<FunctionEntity> findAllBy();

    Stream<FunctionEntity> streamAllBy();

    Optional<FunctionEntity> findByHash(Long id);

    List<FunctionEntity> findByName(String functionName);
}