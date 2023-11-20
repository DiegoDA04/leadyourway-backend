package pe.gob.reniec.platform.consultationservice.infrastructure.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import pe.gob.reniec.platform.consultationservice.infrastructure.persistence.PersonRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PersonDataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (personRepository.count() == 0) {
            try {
                Path sqlFilePath = Paths.get("consultation-service/src/main/resources/data.sql");
                String sql = Files.readString(sqlFilePath);
                jdbcTemplate.execute(sql);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
