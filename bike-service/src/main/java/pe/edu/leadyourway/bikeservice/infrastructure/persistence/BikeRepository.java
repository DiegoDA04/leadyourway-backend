package pe.edu.leadyourway.bikeservice.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.leadyourway.bikeservice.domain.model.Bike;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

}
