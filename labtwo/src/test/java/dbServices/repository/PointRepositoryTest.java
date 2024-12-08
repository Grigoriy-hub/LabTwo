package dbServices.repository;
import dbServices.model.FunctionEntity;
import dbServices.model.PointEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PointRepositoryTest {
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private FunctionRepository functionRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    @Transactional
    public void setUp() {
        entityManager.createQuery("DELETE FROM PointEntity").executeUpdate();
        entityManager.createQuery("DELETE FROM FunctionEntity").executeUpdate();

        FunctionEntity entity = new FunctionEntity();
        entity.setName("Test Function");
        entity.setXFrom(1.0);
        entity.setXTo(5.0);
        entity.setCount(5);
        FunctionEntity functionEntity = functionRepository.save(entity);

        PointEntity pointEntity1 = new PointEntity();
        pointEntity1.setFunction(functionEntity);
        pointEntity1.setX(1.0);
        pointEntity1.setY(1.0);
        pointRepository.save(pointEntity1);

        PointEntity pointEntity2 = new PointEntity();
        pointEntity2.setFunction(functionEntity);
        pointEntity2.setX(2.0);
        pointEntity2.setY(2.0);
        pointRepository.save(pointEntity2);

        PointEntity pointEntity3 = new PointEntity();
        pointEntity3.setFunction(functionEntity);
        pointEntity3.setX(3.0);
        pointEntity3.setY(3.0);
        pointRepository.save(pointEntity3);
    }

    @Test
    @Rollback(value = true)
    public void testFindByFunction() {
        FunctionEntity functionEntity = functionRepository.findAll().get(0);
        List<PointEntity> points = pointRepository.findByFunction(functionEntity);
        assertEquals(3, points.size());
    }

}