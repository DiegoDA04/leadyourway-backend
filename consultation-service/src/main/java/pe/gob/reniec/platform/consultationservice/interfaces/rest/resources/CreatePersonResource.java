package pe.gob.reniec.platform.consultationservice.interfaces.rest.resources;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
@With
public class CreatePersonResource {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Length(min = 8, max = 8)
    private String dni;

    @NotNull
    private String birthdate;
}
