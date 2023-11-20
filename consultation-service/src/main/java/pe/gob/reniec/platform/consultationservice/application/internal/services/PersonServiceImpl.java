package pe.gob.reniec.platform.consultationservice.application.internal.services;

import org.springframework.stereotype.Service;
import pe.gob.reniec.platform.consultationservice.domain.exceptions.ResourceNotFoundException;
import pe.gob.reniec.platform.consultationservice.domain.models.Person;
import pe.gob.reniec.platform.consultationservice.domain.services.PersonService;
import pe.gob.reniec.platform.consultationservice.infrastructure.persistence.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Person getById(Long personId) {
        return personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person", personId));
    }

    @Override
    public Optional<Person> getByDni(String dni) {
        return personRepository.findByDni(dni);
    }

    @Override
    public Optional<Person> getPerson(String firstName, String lastName, String dni) {
        return personRepository.findByFirstNameAndLastNameAndDni(firstName, lastName, dni);
    }
}
