package pe.gob.reniec.platform.consultationservice.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@With
@Table(name = "persons", uniqueConstraints = @UniqueConstraint(columnNames = "dni"))
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Length(min = 8, max = 8)
    private String dni;

    @NotNull
    private String birthdate;
}
