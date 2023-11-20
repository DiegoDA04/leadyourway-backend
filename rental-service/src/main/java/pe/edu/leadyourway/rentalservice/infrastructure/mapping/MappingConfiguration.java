package pe.edu.leadyourway.rentalservice.infrastructure.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("rentalServiceConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper() {
        return new EnhancedModelMapper();
    }

    @Bean
    public RentalMapper rentalMapper(){ return new RentalMapper(); }
}