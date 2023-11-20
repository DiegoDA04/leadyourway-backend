package pe.gob.reniec.platform.consultationservice.domain.services;


import pe.gob.reniec.platform.consultationservice.domain.models.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person create(Person person);
    List<Person> getAll();
    Person getById(Long personId);
    Optional<Person> getPerson(String firstName, String lastName, String dni);
    Optional<Person> getByDni(String dni);
}
