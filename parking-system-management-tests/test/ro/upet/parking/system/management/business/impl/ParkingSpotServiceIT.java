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
import ro.upet.parking.system.management.business.api.parking.spot.ParkingSpotService;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.model.parking.spot.ImtParkingSpot;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ParkingSpotServiceIT extends BusinessTests {
    
    @Inject
    protected ParkingSpotRepository parkingSpotRepo;
    
    @Inject
    protected ParkingSpotService parkingSpotService;
    
    
    private static final ParkingSpot PARKING_SPOT_1 = ImtParkingSpot.builder()
    		.number("A1")
    		.parkingLevelNumber("")
    		.parkingZoneLetter("")
    		.parkingName("")
			.build();
	
    @Test
    @Transactional
    public void addParkingSpot_test_success() {
        final ParkingSpot actualResult = parkingSpotService.add(PARKING_SPOT_1);
        final ParkingSpot expectedResult = ImtParkingSpot.copyOf(PARKING_SPOT_1)
        											.withId(actualResult.getId())
        											.withCode(actualResult.getCode())
        											.withAvailable(true)
        											.withRentable(false)
        											.withRented(false)
        											.withCreatedAt(actualResult.getCreatedAt())
        											.withUpdatedAt(actualResult.getUpdatedAt());
        assertThat(actualResult).isNotNull();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    
    @Test
    @Transactional
    public void deleteParkingSpotById_test_success() {
    	ParkingSpotEntity pse = new ParkingSpotEntity();
    	pse.setNumber("A1");
    	pse = parkingSpotRepo.save(pse);
    	
    	final List<ParkingSpot> actualResult = parkingSpotService.getList();	
        parkingSpotService.removeById(pse.getId());
        final List<ParkingSpot> expectedResult = parkingSpotService.getList();
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size() - 1).isEqualTo(expectedResult.size());
    }
    
    @Test
    @Transactional
    public void findParkingSpotById_test_success() {
    	ParkingSpotEntity pse = new ParkingSpotEntity();
    	pse.setNumber("A1");
    	pse = parkingSpotRepo.save(pse);
        final ParkingSpot actualResult = parkingSpotService.getById(pse.getId());
        final ParkingSpot expectedResult = ImtParkingSpot.builder()
    											.id(actualResult.getId())
    											.createdAt(actualResult.getCreatedAt())
    											.updatedAt(actualResult.getUpdatedAt())
    											.number("A1")
    											.build();
        assertThat(actualResult).isNotNull();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    
    
    @Test
    @Transactional
    public void findAllParkingSpots_test_success() {
    	ParkingSpotEntity pse = new ParkingSpotEntity();
    	parkingSpotRepo.save(pse);
    	
    	pse = new ParkingSpotEntity();
    	parkingSpotRepo.save(pse);
    	
        final List<ParkingSpot> actualResult = parkingSpotService.getList();	
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size()).isEqualTo(2);
    }
    
    @Test
    @Transactional
    public void updateParkingSpot_test_success() {
       	ParkingSpotEntity pse = new ParkingSpotEntity();
    	pse = parkingSpotRepo.save(pse);
        final ParkingSpot actualResult = parkingSpotService.update(ImtParkingSpot.copyOf(PARKING_SPOT_1).withId(pse.getId()));
        final ParkingSpot expectedResult = ImtParkingSpot.copyOf(PARKING_SPOT_1)
        											.withId(actualResult.getId())
        											.withAvailable(true)
        											.withRentable(false)
        											.withCreatedAt(actualResult.getCreatedAt())
        											.withUpdatedAt(actualResult.getUpdatedAt());
        assertThat(actualResult).isNotNull();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    
    

    
   
}
