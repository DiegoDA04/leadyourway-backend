package pe.gob.reniec.platform.consultationservice.interfaces.rest.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import pe.gob.reniec.platform.consultationservice.domain.models.Person;

@AllArgsConstructor
@NoArgsConstructor
@Data
@With
public class ResponsePersonValidationResource {
    private boolean exists;
    private String message;
    private Person data;
}
