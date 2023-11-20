package pe.edu.leadyourway.rentalservice.interfaces.rest.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRentalResource {
    private Long userId;
    private Long bikeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double totalAmount;
}
