package dbServices.controller;

import dbServices.model.UserEntity;
import dbServices.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class UserControllerUnitTest {

    private UserController userController;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        userRepository = mock(UserRepository.class);
        userController = new UserController();

        var field = UserController.class.getDeclaredField("userRepository");
        field.setAccessible(true);
        field.set(userController, userRepository);
    }

    @Test
    void createUser_ShouldReturnCreatedUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("testuser");
        userEntity.setPassword("password");
        userEntity.setEmail("test@example.com");

        Mockito.when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        ResponseEntity<UserEntity> response = userController.createUser(userEntity);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("testuser", response.getBody().getUsername());
    }

    @Test
    void createUser_ShouldReturnBadRequestIfUserExists() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testuser");

        Mockito.when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(userEntity));

        ResponseEntity<UserEntity> response = userController.createUser(userEntity);

        assertEquals(400, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void getUserById_ShouldReturnUserWhenFound() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("testuser");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));

        ResponseEntity<UserEntity> response = userController.getUserById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("testuser", response.getBody().getUsername());
    }

    @Test
    void getUserById_ShouldReturnNotFoundWhenUserNotFound() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<UserEntity> response = userController.getUserById(1L);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void getUserByName_ShouldReturnUserWhenFound() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testuser");

        Mockito.when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(userEntity));

        ResponseEntity<UserEntity> response = userController.getUserByName("testuser");

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("testuser", response.getBody().getUsername());
    }

    @Test
    void getUserByName_ShouldReturnNotFoundWhenUserNotFound() {
        Mockito.when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        ResponseEntity<UserEntity> response = userController.getUserByName("testuser");

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
