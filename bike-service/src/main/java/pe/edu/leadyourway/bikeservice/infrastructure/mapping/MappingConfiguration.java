package pe.edu.leadyourway.bikeservice.infrastructure.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("bikeServiceConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper() {
        return new EnhancedModelMapper();
    }

    @Bean
    public BikeMapper bikeMapper(){ return new BikeMapper(); }
}