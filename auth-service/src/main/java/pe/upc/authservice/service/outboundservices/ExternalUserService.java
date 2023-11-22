package pe.upc.authservice.service.outboundservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.upc.authservice.domain.entities.UserResource;

@FeignClient(name = "USER-SERVICE", url = "http://api-gateway:8080")
public interface ExternalUserService {

    @GetMapping("/api/v1/users/email/{email}")
    ResponseEntity<UserResource> getUserByEmail(@PathVariable String email);
}
