package pe.edu.leadyourway.userservice.infrastructure.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("userServiceConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper() {
        return new EnhancedModelMapper();
    }

    @Bean
    public UserMapper userMapper(){ return new UserMapper(); }
}