package pe.edu.leadyourway.bikeservice.infrastructure.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.leadyourway.bikeservice.domain.model.Bike;
import pe.edu.leadyourway.bikeservice.interfaces.rest.resources.BikeResource;
import pe.edu.leadyourway.bikeservice.interfaces.rest.resources.CreateBikeResource;

import java.io.Serializable;
import java.util.List;

public class BikeMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public BikeResource toResource(Bike model) {
        return mapper.map(model, BikeResource.class);
    }

    public Bike toModel(CreateBikeResource resource) {
        return mapper.map(resource, Bike.class);
    }

    public List<BikeResource> modelListPage(List<Bike> modelList) {
        // mapper.addConverter(config(resource));
        return mapper.mapList(modelList, BikeResource.class);
    }
}
