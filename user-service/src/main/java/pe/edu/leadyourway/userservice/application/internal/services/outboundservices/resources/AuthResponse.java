package pe.edu.leadyourway.userservice.application.internal.services.outboundservices.resources;

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
