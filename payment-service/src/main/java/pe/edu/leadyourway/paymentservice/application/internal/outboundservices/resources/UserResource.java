package pe.edu.leadyourway.paymentservice.application.internal.outboundservices.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResource {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthdate;
    private String phone;
}
