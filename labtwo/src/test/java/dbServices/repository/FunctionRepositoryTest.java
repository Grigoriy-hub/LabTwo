package dbServices.repository;

import org.junit.jupiter.api.Test;

import dbServices.model.FunctionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FunctionRepositoryTest {
    @Autowired
    private FunctionRepository functionRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @BeforeEach
    @Transactional
    public void setUp() {
        entityManager.createQuery("DELETE FROM FunctionEntity").executeUpdate();
        FunctionEntity functionEntity = new FunctionEntity();
        functionEntity.setName("Test Function");
        functionRepository.save(functionEntity);
    }
    @Test
    @Rollback(value = true)
    public void testFindByName() {
        List<FunctionEntity> functions = functionRepository.findByName("Test Function");
        assertEquals(1, functions.size());
    }
}