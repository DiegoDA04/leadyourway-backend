package pe.edu.leadyourway.rentalservice.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.leadyourway.rentalservice.domain.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

}
