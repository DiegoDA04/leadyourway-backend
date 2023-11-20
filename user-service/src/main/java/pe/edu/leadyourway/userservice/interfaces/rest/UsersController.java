package pe.edu.leadyourway.userservice.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.leadyourway.userservice.application.internal.services.outboundservices.ExternalReniecService;
import pe.edu.leadyourway.userservice.application.internal.services.outboundservices.resources.ResponseValidationResource;
import pe.edu.leadyourway.userservice.domain.exceptions.ResourceValidationException;
import pe.edu.leadyourway.userservice.domain.services.UserService;
import pe.edu.leadyourway.userservice.infrastructure.mapping.UserMapper;
import pe.edu.leadyourway.userservice.interfaces.rest.resources.CreateUserResource;
import pe.edu.leadyourway.userservice.interfaces.rest.resources.UserResource;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final ExternalReniecService externalReniecService;
    private final UserService userService;
    private final UserMapper mapper;

    public UsersController(ExternalReniecService externalReniecService, UserService userService, UserMapper mapper) {
        this.externalReniecService = externalReniecService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {

        ResponseValidationResource response = externalReniecService.validatePerson(mapper.toModel(resource));

        System.out.println(response);
        if(!response.isExists())
            throw new ResourceValidationException("User doesn't exists in RENIEC");

        return new ResponseEntity<>(mapper.toResource(userService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        return new ResponseEntity<>(mapper.modelListPage(userService.getAll()), HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(mapper.toResource(userService.getById(userId)), HttpStatus.OK);
    }
}
