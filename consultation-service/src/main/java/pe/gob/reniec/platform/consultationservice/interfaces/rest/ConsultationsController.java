package pe.gob.reniec.platform.consultationservice.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.reniec.platform.consultationservice.application.internal.services.PersonServiceImpl;
import pe.gob.reniec.platform.consultationservice.domain.exceptions.ResourceNotFoundException;
import pe.gob.reniec.platform.consultationservice.domain.models.Person;
import pe.gob.reniec.platform.consultationservice.interfaces.rest.resources.CreatePersonResource;
import pe.gob.reniec.platform.consultationservice.interfaces.rest.resources.RequestPersonValidationResource;
import pe.gob.reniec.platform.consultationservice.interfaces.rest.resources.ResponsePersonValidationResource;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/consultations")
public class ConsultationsController {

    private final PersonServiceImpl personService;

    public ConsultationsController(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody CreatePersonResource resource) {

        Person person = new Person()
                .withFirstName(resource.getFirstName())
                .withLastName(resource.getLastName())
                .withDni(resource.getDni())
                .withBirthdate(resource.getBirthdate());

        return new ResponseEntity<>(personService.create(person), HttpStatus.CREATED);
    }

    @PostMapping("/validate")
    public ResponseEntity<ResponsePersonValidationResource> validatePerson(@RequestBody RequestPersonValidationResource request) {

        Optional<Person> existPerson = personService.getPerson(request.getFirstName(), request.getLastName(), request.getDni());

        if(existPerson.isEmpty()) {
            ResponsePersonValidationResource response = new ResponsePersonValidationResource()
                    .withData(null)
                    .withMessage("The person with name " + request.getFirstName() + " " + request.getLastName() + " and DNI " + request.getDni() + " doesn't exists")
                    .withExists(false);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        ResponsePersonValidationResource response = new ResponsePersonValidationResource()
                .withData(existPerson.get())
                .withMessage("The person does exists")
                .withExists(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Person>> getAllPeople() {
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }

    @GetMapping("dni/{dni}")
    public ResponseEntity<Person> getPersonByDni(@PathVariable String dni) {

        Person existPerson = personService.getByDni(dni).orElseThrow(() -> new ResourceNotFoundException("Person", dni));



        return new ResponseEntity<>(existPerson, HttpStatus.OK);
    }

    @GetMapping("{personId}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long personId) {
        return new ResponseEntity<>(personService.getById(personId), HttpStatus.OK);
    }
}
