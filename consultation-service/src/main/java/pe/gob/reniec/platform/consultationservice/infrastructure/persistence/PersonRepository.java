package pe.gob.reniec.platform.consultationservice.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.gob.reniec.platform.consultationservice.domain.models.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByFirstNameAndLastNameAndDni(String firstName, String LastName, String dni);
    Optional<Person> findByDni(String dni);
    Boolean existsByDni(String dni);
}
