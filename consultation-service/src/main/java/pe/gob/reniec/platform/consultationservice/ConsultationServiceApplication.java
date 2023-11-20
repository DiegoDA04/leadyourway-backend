package pe.gob.reniec.platform.consultationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pe.gob.reniec.platform.consultationservice.infrastructure.initializer.PersonDataInitializer;

@SpringBootApplication
public class ConsultationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultationServiceApplication.class, args);
	}

	@Bean
	public PersonDataInitializer initializer() {
		return new PersonDataInitializer();
	}
}
