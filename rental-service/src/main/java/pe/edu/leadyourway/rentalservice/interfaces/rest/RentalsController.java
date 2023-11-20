package pe.edu.leadyourway.rentalservice.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.leadyourway.rentalservice.application.internal.outboundservices.ExternalBikeService;
import pe.edu.leadyourway.rentalservice.application.internal.outboundservices.ExternalUserService;
import pe.edu.leadyourway.rentalservice.application.internal.outboundservices.resources.BikeResource;
import pe.edu.leadyourway.rentalservice.domain.exceptions.ResourceNotFoundException;
import pe.edu.leadyourway.rentalservice.domain.model.Rental;
import pe.edu.leadyourway.rentalservice.domain.services.RentalService;
import pe.edu.leadyourway.rentalservice.infrastructure.mapping.RentalMapper;
import pe.edu.leadyourway.rentalservice.interfaces.rest.resources.CreateRentalResource;
import pe.edu.leadyourway.rentalservice.interfaces.rest.resources.RentalResource;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rentals")
public class RentalsController {
    private final RentalService rentalService;
    private final ExternalUserService externalUserService;
    private final ExternalBikeService externalBikeService;
    private final RentalMapper mapper;

    public RentalsController(RentalService rentalService, ExternalUserService externalUserService, ExternalBikeService externalBikeService, RentalMapper mapper) {
        this.rentalService = rentalService;
        this.externalUserService = externalUserService;
        this.externalBikeService = externalBikeService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Rental> createRental(@RequestBody CreateRentalResource resource) {

        var userResponse = externalUserService.getUserById(resource.getUserId());

        if(userResponse.getStatusCode() != HttpStatus.OK)
            throw new ResourceNotFoundException("User", resource.getUserId());

        var bikeResponse = externalBikeService.getBikeById(resource.getBikeId());

        if(bikeResponse.getStatusCode() != HttpStatus.OK)
            throw new ResourceNotFoundException("Bike", resource.getBikeId());

        return new ResponseEntity<>(rentalService.create(mapper.toModel(resource)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RentalResource>> getAllRentals() {


        List<Rental> rentals = rentalService.getAll();
        List<RentalResource> rentalResources = rentals.stream().map(
                bike -> {
                    return getRentalById(bike.getId()).getBody();
                }
        ).collect(Collectors.toList());


        return new ResponseEntity<>(rentalResources, HttpStatus.OK);
    }

    @GetMapping("{rentalId}")
    public ResponseEntity<RentalResource> getRentalById(@PathVariable Long rentalId) {

        Rental rental = rentalService.getById(rentalId);

        var userResponse = externalUserService.getUserById(rental.getUserId());
        var bikeResponse = externalBikeService.getBikeById(rental.getBikeId());

        RentalResource rentalResource =  mapper.toResource(rental);
        rentalResource.setUser(userResponse.getBody());
        rentalResource.setBike(bikeResponse.getBody());

        return new ResponseEntity<>(rentalResource, HttpStatus.OK);
    }
}
