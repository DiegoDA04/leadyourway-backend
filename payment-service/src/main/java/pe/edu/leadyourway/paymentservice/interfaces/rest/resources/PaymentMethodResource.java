package pe.edu.leadyourway.paymentservice.interfaces.rest.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentMethodResource {
    private Long id;
    private String name;
    private UUID cardNumber;
}
