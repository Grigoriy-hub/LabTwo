package dbServices.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class UserEntityTest {

    @Test
    public void testGetAndSetId() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    public void testGetAndSetUsername() {
        UserEntity user = new UserEntity();
        String username = "testuser";
        user.setUsername(username);
        assertEquals(username, user.getUsername());
    }

    @Test
    public void testGetAndSetPassword() {
        UserEntity user = new UserEntity();
        String password = "securepassword";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testGetAndSetEmail() {
        UserEntity user = new UserEntity();
        String email = "test@example.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testDefaultRole() {
        UserEntity user = new UserEntity();
        assertEquals("USER", user.getRole());
    }

    @Test
    public void testSetRole() {
        UserEntity user = new UserEntity();
        String role = "ADMIN";
        user.setRole(role);
        assertEquals(role, user.getRole());
    }

    @Test
    public void testCreatedAtIsNotNullByDefault() {
        UserEntity user = new UserEntity();
        assertNotNull(user.getCreatedAt());
    }


    @Test
    public void testEntityProperties() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("securepassword");
        user.setEmail("test@example.com");
        user.setRole("ADMIN");

        assertAll(
                () -> assertEquals(1L, user.getId()),
                () -> assertEquals("testuser", user.getUsername()),
                () -> assertEquals("securepassword", user.getPassword()),
                () -> assertEquals("test@example.com", user.getEmail()),
                () -> assertEquals("ADMIN", user.getRole())
        );
    }
}
