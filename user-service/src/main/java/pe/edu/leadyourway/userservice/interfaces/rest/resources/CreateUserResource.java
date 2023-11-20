package pe.edu.leadyourway.userservice.interfaces.rest.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserResource {
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private String password;
    private LocalDate birthdate;
    private String phone;
}
