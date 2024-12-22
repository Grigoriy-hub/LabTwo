package authentication;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Предпочтительно возвращать неизменяемый список для безопасности
        return  List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        // Логика для проверки истечения срока действия учетной записи
        return true; // Можно настроить в будущем
    }

    @Override
    public boolean isAccountNonLocked() {
        // Логика для проверки блокировки учетной записи
        return true; // Можно настроить в будущем
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Логика для проверки истечения срока действия учетных данных
        return true; // Можно настроить в будущем
    }

    @Override
    public boolean isEnabled() {
        // Логика для проверки активации учетной записи
        return true; // Можно настроить в будущем
    }
}
