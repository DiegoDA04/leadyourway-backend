package pe.edu.leadyourway.bikeservice.domain.services;

import pe.edu.leadyourway.bikeservice.domain.model.Bike;

import java.util.List;

public interface BikeService {
    Bike create(Bike bike);
    List<Bike> getAll();
    Bike getById(Long bikeId);
}
