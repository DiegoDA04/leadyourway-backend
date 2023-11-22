package pe.upc.authservice.domain.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@With
public class AuthResponse {
    private Boolean success;
    private RegisterResponse data;
    private String message;
}
