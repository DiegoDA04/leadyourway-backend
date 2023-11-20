package pe.edu.leadyourway.rentalservice.domain.services;


import pe.edu.leadyourway.rentalservice.domain.model.Rental;

import java.util.List;

public interface RentalService {
    Rental create(Rental bike);
    List<Rental> getAll();
    Rental getById(Long bikeId);
}
