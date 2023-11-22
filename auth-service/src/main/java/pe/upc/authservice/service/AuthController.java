package pe.upc.authservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.authservice.domain.entities.AuthResponse;
import pe.upc.authservice.domain.entities.LoginRequest;
import pe.upc.authservice.domain.entities.LoginResponse;
import pe.upc.authservice.domain.entities.RegisterRequest;
import pe.upc.authservice.exceptions.ResourceValidationException;
import pe.upc.authservice.service.outboundservices.ExternalUserService;

@RestController()
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ExternalUserService userService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Login",
            description = "Login user",
            tags = {"auth"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User logged in successfully",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = LoginResponse.class)),
                    links = @io.swagger.v3.oas.annotations.links.Link(name = "no links")
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User not authenticated",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "text/plain", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = String.class)),
                    links = @io.swagger.v3.oas.annotations.links.Link(name = "no links")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "text/plain", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = String.class)),
                    links = @io.swagger.v3.oas.annotations.links.Link(name = "no links")
            )
    })
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        var response = userService.getUserByEmail(loginRequest.getEmail());

        if(response.getStatusCode() != HttpStatus.OK)
            throw new ResourceValidationException("User is not registered in the system");

        String token = JwtUtil.generateToken(loginRequest);
        LoginResponse loginResponse = new LoginResponse(token);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);

        /*
        if (loginRequest != null && authService.checkPassword(loginRequest.getPassword(), loginRequest.getPassword())) {
            String token = JwtUtil.generateToken(loginRequest);
            return ResponseEntity.ok(new LoginResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }*/
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Register",
            description = "Register a user",
            tags = {"auth"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User registered successfully",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = String.class)),
                    links = @io.swagger.v3.oas.annotations.links.Link(name = "no links")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = String.class)),
                    links = @io.swagger.v3.oas.annotations.links.Link(name = "no links")
            )
    })
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(new AuthResponse().withSuccess(true).withData(authService.register(registerRequest)).withMessage("Successfully register"), HttpStatus.CREATED);
    }
}
