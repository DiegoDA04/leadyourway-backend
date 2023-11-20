package pe.edu.leadyourway.userservice.domain.services;

import pe.edu.leadyourway.userservice.domain.model.User;

import java.util.List;

public interface UserService {
    User create(User user);
    List<User> getAll();
    User getById(Long userId);
}
