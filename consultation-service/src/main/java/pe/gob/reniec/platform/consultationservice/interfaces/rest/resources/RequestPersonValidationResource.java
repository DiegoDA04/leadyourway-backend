package pe.gob.reniec.platform.consultationservice.interfaces.rest.resources;

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
