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
import ro.upet.parking.system.management.business.api.parking.zone.ParkingZoneService;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;
import ro.upet.parking.system.management.data.impl.parking.zone.ParkingZoneRepository;
import ro.upet.parking.system.management.model.parking.zone.ImtParkingZone;
import ro.upet.parking.system.management.model.parking.zone.ParkingZone;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ParkingZoneServiceIT extends BusinessTests {
    
    @Inject
    protected ParkingZoneRepository parkingZoneRepo;
    
    @Inject
    protected ParkingZoneService parkingZoneService;
    
    
    private static final ParkingZone PARKING_ZONE_1 = ImtParkingZone.builder()
    		.letter("A")
			.build();
	
    
    @Test
    @Transactional
    public void addParkingZone_test_success() {
        final ParkingZone actualResult = parkingZoneService.add(PARKING_ZONE_1);
        final ParkingZone expectedResult = ImtParkingZone.copyOf(PARKING_ZONE_1)
        											.withId(actualResult.getId())
        											.withCreatedAt(actualResult.getCreatedAt())
        											.withUpdatedAt(actualResult.getUpdatedAt());
        assertThat(actualResult).isNotNull();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    
    @Test
    @Transactional
    public void deleteParkingZoneById_test_success() {
    	ParkingZoneEntity pze = new ParkingZoneEntity();
    	pze.setLetter("A");
    	pze = parkingZoneRepo.save(pze);
    	
    	final List<ParkingZone> actualResult = parkingZoneService.getList();	
        parkingZoneService.removeById(pze.getBase().getId());
        final List<ParkingZone> expectedResult = parkingZoneService.getList();
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size() - 1).isEqualTo(expectedResult.size());
    }
    
    @Test
    @Transactional
    public void findParkingZoneById_test_success() {
    	ParkingZoneEntity pze = new ParkingZoneEntity();
    	pze.setLetter("A");
    	pze = parkingZoneRepo.save(pze);
        final ParkingZone actualResult = parkingZoneService.getById(pze.getBase().getId());
        final ParkingZone expectedResult = ImtParkingZone.builder()
    											.id(actualResult.getId())
    											.createdAt(actualResult.getCreatedAt())
    											.updatedAt(actualResult.getUpdatedAt())
    											.letter("A")
    											.build();
        assertThat(actualResult).isNotNull();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    
    
    @Test
    @Transactional
    public void findAllParkingZones_test_success() {
    	ParkingZoneEntity pze = new ParkingZoneEntity();
    	parkingZoneRepo.save(pze);
    	
    	pze = new ParkingZoneEntity();
    	parkingZoneRepo.save(pze);
    	
        final List<ParkingZone> actualResult = parkingZoneService.getList();	
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size()).isEqualTo(2);
    }
    
    @Test
    @Transactional
    public void updateParkingZone_test_success() {
       	ParkingZoneEntity pze = new ParkingZoneEntity();
    	pze = parkingZoneRepo.save(pze);
        final ParkingZone actualResult = parkingZoneService.update(ImtParkingZone.copyOf(PARKING_ZONE_1).withId(pze.getBase().getId()));
        final ParkingZone expectedResult = ImtParkingZone.copyOf(PARKING_ZONE_1)
        											.withId(actualResult.getId())
        											.withCreatedAt(actualResult.getCreatedAt())
        											.withUpdatedAt(actualResult.getUpdatedAt());
        assertThat(actualResult).isNotNull();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    
    

    
   
}
