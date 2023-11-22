package pe.edu.leadyourway.userservice.application.internal.services.outboundservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pe.edu.leadyourway.userservice.application.internal.services.outboundservices.resources.AuthResponse;
import pe.edu.leadyourway.userservice.application.internal.services.outboundservices.resources.RegisterRequest;

@FeignClient(name = "AUTH-SERVICE", url = "http://api-gateway:8080")
public interface ExternalAuthService {

    @PostMapping("/api/v1/auth/register")
    ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest);
}
