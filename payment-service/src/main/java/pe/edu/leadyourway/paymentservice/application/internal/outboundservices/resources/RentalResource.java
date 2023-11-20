package pe.edu.leadyourway.paymentservice.application.internal.outboundservices.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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