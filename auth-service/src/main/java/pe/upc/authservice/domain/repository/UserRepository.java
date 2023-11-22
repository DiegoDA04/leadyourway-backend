package pe.upc.authservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.authservice.domain.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
