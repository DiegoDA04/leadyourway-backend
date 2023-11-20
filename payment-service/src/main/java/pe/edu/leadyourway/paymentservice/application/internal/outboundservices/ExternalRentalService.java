package pe.edu.leadyourway.paymentservice.application.internal.outboundservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.leadyourway.paymentservice.application.internal.outboundservices.resources.RentalResource;


@FeignClient(name="RENTAL-SERVICE")
public interface ExternalRentalService {

    @GetMapping("/api/v1/rentals/{rentalId}")
    ResponseEntity<RentalResource> getRentalById(@PathVariable Long rentalId);
}
