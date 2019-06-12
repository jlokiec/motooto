package pl.motooto.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.motooto.webapp.model.User;

public interface UserDao extends JpaRepository<User, Long> {
    User save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
}
