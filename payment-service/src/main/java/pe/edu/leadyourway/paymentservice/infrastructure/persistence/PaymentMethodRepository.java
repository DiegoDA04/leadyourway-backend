package pe.edu.leadyourway.paymentservice.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.leadyourway.paymentservice.domain.models.PaymentMethod;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    Optional<PaymentMethod> findByCardNumber(UUID cardNumber);
}
