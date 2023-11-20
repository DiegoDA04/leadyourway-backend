package pe.edu.leadyourway.userservice.application.internal.services.outboundservices.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseValidationResource {
    private boolean exists;
    private Object data;
    private String message;
}
