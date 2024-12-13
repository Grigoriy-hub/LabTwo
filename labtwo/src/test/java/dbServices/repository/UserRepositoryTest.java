package dbServices.repository;

import dbServices.model.UserEntity;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    @Transactional
    public void setUp() {
        entityManager.createQuery("DELETE FROM UserEntity").executeUpdate();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Test User");
        userEntity.setEmail("world.g@gmail.com");
        userEntity.setPassword("12345");
        userRepository.save(userEntity);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUsername("User123");
        userEntity2.setEmail("hello.g@gmail.com");
        userEntity2.setPassword("99999");
        userRepository.save(userEntity2);
    }

    @Test
    @Rollback(value = true)
    public void testFindByUserName() {
        Optional<UserEntity> user = userRepository.findByUsername("Test Function");
        assertTrue(user.isEmpty());
    }
    @Test
    @Rollback(value = true)
    public void testFindByEmail() {
        Optional<UserEntity> user2 = userRepository.findByEmail("hello.g@gmail.ru");
        assertTrue(user2.isEmpty());

    }
}

