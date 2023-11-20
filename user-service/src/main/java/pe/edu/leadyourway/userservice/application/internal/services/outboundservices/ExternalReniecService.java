package pe.edu.leadyourway.userservice.application.internal.services.outboundservices;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.edu.leadyourway.userservice.application.internal.services.outboundservices.resources.RequestPersonValidationResource;
import pe.edu.leadyourway.userservice.application.internal.services.outboundservices.resources.ResponseValidationResource;
import pe.edu.leadyourway.userservice.domain.model.User;
import pe.edu.leadyourway.userservice.interfaces.rest.resources.UserResource;

@Service
public class ExternalReniecService {

    private static final String URL = "http://localhost:8181/api/v1/consultations";

    private final RestTemplate restTemplate;

    public ExternalReniecService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public ResponseValidationResource validatePerson(User user) {
        RequestPersonValidationResource request = new RequestPersonValidationResource(user.getFirstName(), user.getLastName(), user.getDni());

        return restTemplate.postForObject(URL + "/validate",request, ResponseValidationResource.class );
    }
}
