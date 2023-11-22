package pe.upc.authservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.authservice.domain.entities.LoginRequest;
import pe.upc.authservice.domain.entities.LoginResponse;
import pe.upc.authservice.domain.entities.User;

@RestController()
@RequestMapping("/api/v1/")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
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
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByUsername(loginRequest.getUsername());
        if (user != null && userService.checkPassword(loginRequest.getPassword(), user.getPassword())) {
            String token = JwtUtil.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
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
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
