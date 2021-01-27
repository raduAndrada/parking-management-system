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
import ro.upet.parking.system.management.data.api.parking.ParkingEntity;
import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;
import ro.upet.parking.system.management.data.impl.parking.ParkingRepository;
import ro.upet.parking.system.management.data.impl.parking.level.ParkingLevelRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ParkingLevelRepositoryIT extends DataTests {
	
	    
	    private static final String PARKING_LEVEL_NUMBER1 = "0";
	    private static final String PARKING_LEVEL_NUMBER2 = "1";
	    
	    private static final ParkingLevelEntity PARKING_LEVEL_1 = new ParkingLevelEntity();
	    private static final ParkingLevelEntity PARKING_LEVEL_2 = new ParkingLevelEntity();
	    
	    @Inject
	    private ParkingLevelRepository parkingLevelRepo;
	    
	    @Inject
	    private ParkingRepository parkingRepo;
	    
	    @Before
	    public void init() {
	    	final ParkingEntity temp = parkingRepo.save(new ParkingEntity());
	    	
	    	PARKING_LEVEL_1.setNumber(PARKING_LEVEL_NUMBER1);
	    	PARKING_LEVEL_1.setParking(temp);
	    	
	    	PARKING_LEVEL_2.setNumber(PARKING_LEVEL_NUMBER2);
	    	PARKING_LEVEL_2.setParking(temp);
	    }
	    
	    @Test
	    @Transactional
	    public void addParkingLevel_test_success() {
	        final ParkingLevelEntity testEntity = parkingLevelRepo.save(PARKING_LEVEL_1);
	        final Optional<ParkingLevelEntity> expectedEntity = parkingLevelRepo.findById(testEntity.getBase().getId());
	        assertThat(expectedEntity).isNotNull();
	        assertThat(expectedEntity.get().getNumber()).isEqualTo(PARKING_LEVEL_NUMBER1);
	    }
	    
	    @Test
	    @Transactional
	    public void deleteParkingLevel_test_success() {
	        final ParkingLevelEntity testEntity = parkingLevelRepo.save(PARKING_LEVEL_1);
	        parkingLevelRepo.delete(testEntity);
	        final Optional<ParkingLevelEntity> expectedEntity = parkingLevelRepo.findById(testEntity.getBase().getId());
	        assertThat(expectedEntity.isEmpty());
	    }
	    
	
	    
	    @Test
	    @Transactional
	    public void findAllParkingLevels_test_success() {
	        parkingLevelRepo.save(PARKING_LEVEL_1);
	        parkingLevelRepo.save(PARKING_LEVEL_2);
	        final List<ParkingLevelEntity> expectedList = parkingLevelRepo.findAll();
	        assertThat(expectedList).isNotNull();
	        assertThat(expectedList.size()).isEqualTo(2);
	    }
	    
	    
	    @Test
	    @Transactional
	    public void updateParkingLevel_test_success() {
	        final ParkingLevelEntity testEntity = parkingLevelRepo.save(PARKING_LEVEL_1);
	        testEntity.setNumber(PARKING_LEVEL_NUMBER2);
	        parkingLevelRepo.save(testEntity);
	        final Optional<ParkingLevelEntity> expectedEntity = parkingLevelRepo.findById(testEntity.getBase().getId());
	        assertThat(expectedEntity).isNotNull();
	        assertThat(expectedEntity.get().getNumber()).isEqualTo(PARKING_LEVEL_NUMBER2);
	    }
}

