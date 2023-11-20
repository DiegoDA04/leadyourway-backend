package pe.edu.leadyourway.bikeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class BikeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeServiceApplication.class, args);
	}

}
