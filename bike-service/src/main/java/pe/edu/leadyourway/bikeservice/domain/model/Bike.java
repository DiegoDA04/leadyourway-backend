package pe.edu.leadyourway.bikeservice.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "bikes")
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull
    @Length(max = 50)
    private String name;

    @NotNull
    @Length(max = 230)
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private String model;

    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    private String status;
}