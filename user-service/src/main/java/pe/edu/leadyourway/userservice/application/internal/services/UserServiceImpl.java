package pe.edu.leadyourway.userservice.application.internal.services;

import org.springframework.stereotype.Service;
import pe.edu.leadyourway.userservice.domain.exceptions.ResourceNotFoundException;
import pe.edu.leadyourway.userservice.domain.model.User;
import pe.edu.leadyourway.userservice.domain.services.UserService;
import pe.edu.leadyourway.userservice.infrastructure.persistence.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final String ENTITY = "User";
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long userId) {

        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }
}
