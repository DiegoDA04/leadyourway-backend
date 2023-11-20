package pe.edu.leadyourway.paymentservice.interfaces.rest.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.leadyourway.paymentservice.application.internal.outboundservices.resources.RentalResource;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentResource {
    private Long id;
    private RentalResource rental;
    private PaymentMethodResource paymentMethod;
    private LocalDateTime paymentDate;
    private Double amountPaid;
}
