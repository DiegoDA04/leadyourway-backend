package pe.edu.leadyourway.userservice.application.internal.services;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pe.edu.leadyourway.userservice.application.internal.services.outboundservices.ExternalAuthService;
import pe.edu.leadyourway.userservice.application.internal.services.outboundservices.resources.RegisterRequest;
import pe.edu.leadyourway.userservice.domain.exceptions.ResourceNotFoundException;
import pe.edu.leadyourway.userservice.domain.exceptions.ResourceValidationException;
import pe.edu.leadyourway.userservice.domain.model.User;
import pe.edu.leadyourway.userservice.domain.services.UserService;
import pe.edu.leadyourway.userservice.infrastructure.persistence.UserRepository;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private static final String ENTITY = "User";
    private final UserRepository userRepository;
    private final ExternalAuthService authService;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, ExternalAuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Override
    public User create(User user) {

        if(userRepository.findByDni(user.getDni()).isPresent()) {
            logger.info("An attempt was made to create a user with the same user information registered in the system");

            throw new ResourceValidationException(ENTITY, "User with the same information Data (DNI, firstName, lastName) already exists");
        }

        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            logger.info("An attempt was made to create a user with the same user information registered in the system");

            throw new ResourceValidationException(ENTITY, "User with the same email already exists");
        }
        RegisterRequest request = new RegisterRequest(user.getEmail(), user.getPassword());

        var response = authService.register(request);
        if(response.getStatusCode() == HttpStatus.CREATED)
            user.setPassword(response.getBody().getData().getPassword());

        logger.info("Successfully registered user");
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(String.format("%s with email %s not found.", ENTITY, email)));
    }

    @Override
    public User getById(Long userId) {

        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }
}
