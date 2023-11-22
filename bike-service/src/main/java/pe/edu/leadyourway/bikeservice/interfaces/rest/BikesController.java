package pe.edu.leadyourway.bikeservice.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import pe.edu.leadyourway.bikeservice.application.internal.outboundservices.ExternalUserService;
import pe.edu.leadyourway.bikeservice.domain.exceptions.ResourceNotFoundException;
import pe.edu.leadyourway.bikeservice.domain.model.Bike;
import pe.edu.leadyourway.bikeservice.domain.services.BikeService;
import pe.edu.leadyourway.bikeservice.infrastructure.mapping.BikeMapper;
import pe.edu.leadyourway.bikeservice.interfaces.rest.resources.BikeResource;
import pe.edu.leadyourway.bikeservice.interfaces.rest.resources.CreateBikeResource;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/bikes")
@Tag(name = "BicyclesController", description = "Bicycles Management Endpoints")
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
    @Operation( 
        summary = "Create Bicycle",
        description = "Create Bicycle"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Bicycle created",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bike.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Bicycle not found"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid body"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error"
        )
    })
    public ResponseEntity<Bike> createBike(@RequestBody CreateBikeResource resource) {

        var response = externalUserService.getUserById(resource.getUserId());

        if(response.getStatusCode() != HttpStatus.OK)
            throw new ResourceNotFoundException("User", resource.getUserId());

        return new ResponseEntity<>(bikeService.create(mapper.toModel(resource)), HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    @GetMapping
    @Operation(
        summary="Get Bicycles",
        description = "Get All Bicycles"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Bicycles found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bike.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Bicycles not found"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error"
        )
    })
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

    @Transactional(readOnly = true)
    @GetMapping("{bikeId}")
    @Operation( 
        summary = "Get Bicycle",
        description = "Get Bicycle by Id"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Bicycle found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bike.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Bicycle not found"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid id"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error"
        )
    })
    public ResponseEntity<?> getBikeById(@PathVariable Long bikeId) {

        Bike bike = bikeService.getById(bikeId);

        var response = externalUserService.getUserById(bike.getUserId());

        BikeResource bikeResource =  mapper.toResource(bike);
        bikeResource.setUser(response.getBody());

        return new ResponseEntity<>(bikeResource, HttpStatus.OK);
    }
}
