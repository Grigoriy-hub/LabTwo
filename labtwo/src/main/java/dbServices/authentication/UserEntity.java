package dbServices.authentication;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;
    @Setter
    @Getter
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Setter
    @Getter
    @Column(name = "password", nullable = false)
    private String password;

    public Long getId() {
        return user_id;
    }
    public void setId(Long user_id) {
        this.user_id = user_id;
    }

}
