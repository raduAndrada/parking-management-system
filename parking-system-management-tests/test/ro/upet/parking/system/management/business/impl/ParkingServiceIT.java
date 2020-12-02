package ro.upet.parking.system.management.business.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ro.upet.parking.system.management.business.BusinessTests;
import ro.upet.parking.system.management.business.api.parking.ParkingService;
import ro.upet.parking.system.management.data.api.parking.ParkingEntity;
import ro.upet.parking.system.management.data.impl.parking.ParkingRepository;
import ro.upet.parking.system.management.model.parking.ImtParking;
import ro.upet.parking.system.management.model.parking.Parking;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ParkingServiceIT extends BusinessTests {
    
    @Inject
    protected ParkingRepository parkingRepo;
    
    @Inject
    protected ParkingService parkingService;
    
    
    private static final Parking PARKING_1 = ImtParking.builder()
    		.name("Test parking 1")
    		.opensAt("7:00")
    		.closesAt("22:00")
    		.location("Test location 1")
			.build();
	
    
    @Test
    @Transactional
    public void addParking_test_success() {
        final Parking actualResult = parkingService.addParking(PARKING_1);
        final Parking expectedResult = ImtParking.copyOf(PARKING_1)
        											.withId(actualResult.getId())
        											.withCreatedAt(actualResult.getCreatedAt())
        											.withUpdatedAt(actualResult.getUpdatedAt());
        assertThat(actualResult).isNotNull();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    
    @Test
    @Transactional
    public void deleteParkingById_test_success() {
    	ParkingEntity pe = new ParkingEntity();
    	pe.setName("Test Parking 1");
    	pe = parkingRepo.save(pe);
    	
    	final List<Parking> actualResult = parkingService.getParkingList();	
        parkingService.removeParkingById(pe.getId());
        final List<Parking> expectedResult = parkingService.getParkingList();
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size() - 1).isEqualTo(expectedResult.size());
    }
    
    @Test
    @Transactional
    public void findParkingById_test_success() {
    	ParkingEntity pe = new ParkingEntity();
    	pe.setName("Test Parking 1");
    	pe = parkingRepo.save(pe);
        final Parking actualResult = parkingService.getParkingById(pe.getId());
        final Parking expectedResult = ImtParking.builder()
    											.id(actualResult.getId())
    											.createdAt(actualResult.getCreatedAt())
    											.updatedAt(actualResult.getUpdatedAt())
    											.name("Test Parking 1")
    											.build();
        assertThat(actualResult).isNotNull();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    
    
    @Test
    @Transactional
    public void findAllParkings_test_success() {
    	ParkingEntity pe = new ParkingEntity();
    	parkingRepo.save(pe);
    	
    	pe = new ParkingEntity();
    	parkingRepo.save(pe);
    	
        final List<Parking> actualResult = parkingService.getParkingList();	
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size()).isEqualTo(2);
    }
    
    @Test
    @Transactional
    public void updateParking_test_success() {
       	ParkingEntity pe = new ParkingEntity();
    	pe = parkingRepo.save(pe);
        final Parking actualResult = parkingService.updateParking(ImtParking.copyOf(PARKING_1).withId(pe.getId()));
        final Parking expectedResult = ImtParking.copyOf(PARKING_1)
        											.withId(actualResult.getId())
        											.withCreatedAt(actualResult.getCreatedAt())
        											.withUpdatedAt(actualResult.getUpdatedAt());
        assertThat(actualResult).isNotNull();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    
    

    
   
}
