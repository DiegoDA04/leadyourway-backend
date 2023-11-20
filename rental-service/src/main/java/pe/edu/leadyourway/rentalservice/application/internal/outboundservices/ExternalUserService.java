package pe.edu.leadyourway.rentalservice.application.internal.outboundservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.leadyourway.rentalservice.application.internal.outboundservices.resources.UserResource;

@FeignClient(name = "USER-SERVICE")
public interface ExternalUserService {

    @GetMapping("/api/v1/users/{userId}")
    ResponseEntity<UserResource> getUserById(@PathVariable Long userId);
}
