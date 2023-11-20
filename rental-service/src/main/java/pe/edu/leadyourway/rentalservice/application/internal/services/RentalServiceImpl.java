package pe.edu.leadyourway.rentalservice.application.internal.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.leadyourway.rentalservice.domain.exceptions.ResourceNotFoundException;
import pe.edu.leadyourway.rentalservice.domain.model.Rental;
import pe.edu.leadyourway.rentalservice.domain.services.RentalService;
import pe.edu.leadyourway.rentalservice.infrastructure.persistence.RentalRepository;

import java.util.List;

@Service
@Slf4j
public class RentalServiceImpl implements RentalService {

    private static final String ENTITY = "Rental";

    private final RentalRepository rentalRepository;

    public RentalServiceImpl(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Rental create(Rental rental) {
        rental.setId(null);
        return rentalRepository.save(rental);
    }

    @Override
    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental getById(Long rentalId) {
        return rentalRepository.findById(rentalId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, rentalId));
    }
}
