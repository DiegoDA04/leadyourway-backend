package pe.edu.leadyourway.userservice.application.internal.services.outboundservices.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestPersonValidationResource {
    private String firstName;
    private String lastName;
    private String dni;
}
