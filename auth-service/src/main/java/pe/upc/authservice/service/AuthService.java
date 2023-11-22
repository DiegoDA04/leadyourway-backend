package pe.upc.authservice.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.upc.authservice.domain.entities.RegisterRequest;
import pe.upc.authservice.domain.entities.RegisterResponse;

@Service
public class AuthService {


    public RegisterResponse register(RegisterRequest registerRequest) {

        registerRequest.setPassword(encryptPassword(registerRequest.getPassword()));

        return new RegisterResponse(registerRequest.getEmail(), registerRequest.getPassword());
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    public Boolean checkPassword(String password, String encodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, encodedPassword);
    }
}
