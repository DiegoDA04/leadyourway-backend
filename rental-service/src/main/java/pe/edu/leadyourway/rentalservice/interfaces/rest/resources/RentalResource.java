package pe.edu.leadyourway.rentalservice.interfaces.rest.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.leadyourway.rentalservice.application.internal.outboundservices.resources.BikeResource;
import pe.edu.leadyourway.rentalservice.application.internal.outboundservices.resources.UserResource;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RentalResource {
    private Long id;
    private UserResource user;
    private BikeResource bike;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double totalAmount;
}
