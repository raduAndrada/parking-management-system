package ro.upet.parking.system.management.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("ro.upet.parking.system.management.data.impl")
@EntityScan("ro.upet.parking.system.management.data.api")
public class Application {
	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
