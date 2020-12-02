package ro.upet.parking.system.management.data.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ro.upet.parking.system.management.data.DataTests;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;
import ro.upet.parking.system.management.data.impl.parking.zone.ParkingZoneRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ParkingZoneRepositoryIT extends DataTests {
	
	    
	    private static final String PARKING_ZONE_LETTER1 = "A";
	    private static final String PARKING_ZONE_LETTER2 = "A";
	    
	    private static final ParkingZoneEntity PARKING_ZONE_1 = new ParkingZoneEntity();
	    private static final ParkingZoneEntity PARKING_ZONE_2 = new ParkingZoneEntity();
	    
	    @Before
	    public void init() {
	    	PARKING_ZONE_1.setLetter(PARKING_ZONE_LETTER1);
	    	
	    	PARKING_ZONE_2.setLetter(PARKING_ZONE_LETTER2);
	    }
	    

	    @Inject
	    protected ParkingZoneRepository parkingZoneRepo;
	    
	    @Test
	    @Transactional
	    public void addParkingZone_test_success() {
	        final ParkingZoneEntity testEntity = parkingZoneRepo.save(PARKING_ZONE_1);
	        final Optional<ParkingZoneEntity> expectedEntity = parkingZoneRepo.findById(testEntity.getId());
	        assertThat(expectedEntity).isNotNull();
	        assertThat(expectedEntity.get().getLetter()).isEqualTo(PARKING_ZONE_LETTER1);
	    }
	    
	    @Test
	    @Transactional
	    public void deleteParkingZone_test_success() {
	        final ParkingZoneEntity testEntity = parkingZoneRepo.save(PARKING_ZONE_1);
	        parkingZoneRepo.delete(testEntity);
	        final Optional<ParkingZoneEntity> expectedEntity = parkingZoneRepo.findById(testEntity.getId());
	        assertThat(expectedEntity.isEmpty());
	    }
	    
	
	    
	    @Test
	    @Transactional
	    public void findAllParkingZones_test_success() {
	        parkingZoneRepo.save(PARKING_ZONE_1);
	        parkingZoneRepo.save(PARKING_ZONE_2);
	        final List<ParkingZoneEntity> expectedList = parkingZoneRepo.findAll();
	        assertThat(expectedList).isNotNull();
	        assertThat(expectedList.size()).isEqualTo(2);
	    }
	    
	    
	    @Test
	    @Transactional
	    public void updateParkingZone_test_success() {
	        final ParkingZoneEntity testEntity = parkingZoneRepo.save(PARKING_ZONE_1);
	        testEntity.setLetter(PARKING_ZONE_LETTER2);
	        parkingZoneRepo.save(testEntity);
	        final Optional<ParkingZoneEntity> expectedEntity = parkingZoneRepo.findById(testEntity.getId());
	        assertThat(expectedEntity).isNotNull();
	        assertThat(expectedEntity.get().getLetter()).isEqualTo(PARKING_ZONE_LETTER2);
	    }
}

