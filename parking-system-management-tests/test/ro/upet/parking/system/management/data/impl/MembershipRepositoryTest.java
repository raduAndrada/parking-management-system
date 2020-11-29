package ro.upet.parking.system.management.data.impl;

import javax.inject.Inject;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ro.upet.parking.system.management.data.impl.membership.MembershipRepository;
import ro.upet.parking.system.management.rest.Application;

//JUnit 5 example with Spring Boot >= 2.2.6
@Testcontainers
@SpringBootTest(classes= Application.class)
public class MembershipRepositoryTest {
	
	@Container
	public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer()
	    .withPassword("inmemory")
	    .withUsername("inmemory");
	
	@DynamicPropertySource
	static void postgresqlProperties(DynamicPropertyRegistry registry) {
	    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
	    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
	    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
	  }
	
	@Inject
	private MembershipRepository membershipRepo;
	


}
