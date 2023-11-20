package pe.edu.leadyourway.rentalservice.application.internal.outboundservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.leadyourway.rentalservice.application.internal.outboundservices.resources.BikeResource;

@FeignClient(name = "BIKE-SERVICE")
public interface ExternalBikeService {

    @GetMapping("/api/v1/bikes/{bikeId}")
    ResponseEntity<BikeResource> getBikeById(@PathVariable Long bikeId);
}
