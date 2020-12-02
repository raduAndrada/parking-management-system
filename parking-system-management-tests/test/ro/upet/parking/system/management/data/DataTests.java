package ro.upet.parking.system.management.data;


import org.junit.Ignore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = DataTests.Config.class )
@ActiveProfiles("test")
@Ignore
public class DataTests {
	
	@SpringBootApplication
	@Import({ DataTests.class })
	@EnableJpaRepositories("ro.upet.parking.system.management.data.impl")
	@EntityScan("ro.upet.parking.system.management.data.api")
	public static class Config  {

	}


}



