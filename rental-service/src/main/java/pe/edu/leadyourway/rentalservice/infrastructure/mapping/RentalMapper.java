package pe.edu.leadyourway.rentalservice.infrastructure.mapping;

import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.leadyourway.rentalservice.domain.model.Rental;
import pe.edu.leadyourway.rentalservice.interfaces.rest.resources.CreateRentalResource;
import pe.edu.leadyourway.rentalservice.interfaces.rest.resources.RentalResource;

import java.io.Serializable;
import java.util.List;

public class RentalMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public RentalResource toResource(Rental model) {
        return mapper.map(model, RentalResource.class);
    }

    public Rental toModel(CreateRentalResource resource) {
        mapper.addMappings(new PropertyMap<CreateRentalResource, Rental>() {
            protected void configure() {
                skip(destination.getId());
            }
        });
        return mapper.map(resource, Rental.class);
    }

    public List<RentalResource> modelListPage(List<Rental> modelList) {
        // mapper.addConverter(config(resource));
        return mapper.mapList(modelList, RentalResource.class);
    }
}
