package pe.edu.leadyourway.bikeservice.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.leadyourway.bikeservice.application.internal.outboundservices.ExternalUserService;
import pe.edu.leadyourway.bikeservice.application.internal.outboundservices.resources.UserResource;
import pe.edu.leadyourway.bikeservice.domain.exceptions.ResourceNotFoundException;
import pe.edu.leadyourway.bikeservice.domain.model.Bike;
import pe.edu.leadyourway.bikeservice.domain.services.BikeService;
import pe.edu.leadyourway.bikeservice.infrastructure.mapping.BikeMapper;
import pe.edu.leadyourway.bikeservice.interfaces.rest.resources.BikeResource;
import pe.edu.leadyourway.bikeservice.interfaces.rest.resources.CreateBikeResource;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bikes")
public class BikesController {
    private final BikeService bikeService;
    private final ExternalUserService externalUserService;
    private final BikeMapper mapper;

    public BikesController(BikeService bikeService, ExternalUserService externalUserService, BikeMapper mapper) {
        this.bikeService = bikeService;
        this.externalUserService = externalUserService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Bike> createBike(@RequestBody CreateBikeResource resource) {

        var response = externalUserService.getUserById(resource.getUserId());

        if(response.getStatusCode() != HttpStatus.OK)
            throw new ResourceNotFoundException("User", resource.getUserId());

        return new ResponseEntity<>(bikeService.create(mapper.toModel(resource)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BikeResource>> getAllBikes() {


        List<Bike> bikes = bikeService.getAll();
        List<BikeResource> bikeResources = bikes.stream().map(
                bike -> {
                    var response = externalUserService.getUserById(bike.getUserId());

                    BikeResource bikeResource = mapper.toResource(bike);
                    bikeResource.setUser(response.getBody());

                    return bikeResource;
                }
        ).collect(Collectors.toList());


        return new ResponseEntity<>(bikeResources, HttpStatus.OK);
    }

    @GetMapping("{bikeId}")
    public ResponseEntity<?> getBikeById(@PathVariable Long bikeId) {

        Bike bike = bikeService.getById(bikeId);

        var response = externalUserService.getUserById(bike.getUserId());

        BikeResource bikeResource =  mapper.toResource(bike);
        bikeResource.setUser(response.getBody());

        return new ResponseEntity<>(bikeResource, HttpStatus.OK);
    }
}
