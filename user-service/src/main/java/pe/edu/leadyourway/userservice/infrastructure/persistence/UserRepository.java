package pe.edu.leadyourway.userservice.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.leadyourway.userservice.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
