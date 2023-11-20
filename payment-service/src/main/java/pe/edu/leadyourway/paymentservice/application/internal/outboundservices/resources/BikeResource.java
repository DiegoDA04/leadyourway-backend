package pe.edu.leadyourway.paymentservice.application.internal.outboundservices.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BikeResource {
    private Long id;
    private UserResource user;
    private String name;
    private String description;
    private Double price;
    private String model;
    private String imageUrl;
    private String status;
}
