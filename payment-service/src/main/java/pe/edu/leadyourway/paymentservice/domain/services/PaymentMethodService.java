package pe.edu.leadyourway.paymentservice.domain.services;

import pe.edu.leadyourway.paymentservice.domain.models.PaymentMethod;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentMethodService{
    PaymentMethod create(PaymentMethod paymentMethod);
    List<PaymentMethod> getAll();
    Optional<PaymentMethod> getById(Long paymentMethodId);

    Optional<PaymentMethod> getByCardNumber(UUID cardNumber);
}
