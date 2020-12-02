package ro.upet.parking.system.management.business;


import org.junit.Ignore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import ro.upet.parking.system.management.rest.config.ApplicationConfig;

@ContextConfiguration(classes = {BusinessTests.Config.class, ApplicationConfig.class} )
@ActiveProfiles("test")
@Ignore
public class BusinessTests {
	
	@SpringBootApplication
	@Import({ BusinessTests.class })
	@EnableJpaRepositories("ro.upet.parking.system.management.data.impl")
	@EntityScan("ro.upet.parking.system.management.data.api")
	public static class Config  {

	}


}



