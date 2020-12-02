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
import ro.upet.parking.system.management.data.impl.parking.ParkingRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ParkingRepositoryIT extends DataTests {
	
		private static final String PARKING_LICENCE_PLATE1 = "email@example.com";
	    private static final String PARKING_LICENCE_PLATE2 = "email2@example.com";
	    
	    private static final String PARKING_NAME1 = "parkingname1";
	    private static final String PARKING_NAME2 = "parkingname2";
	    
	    private static final String PARKING_OPENING_HOUR1 = "06:00";
	    private static final String PARKING_OPENING_HOUR2 = "07:45";

	    private static final String PARKING_CLOSING_HOUR1 = "22:00";
	    private static final String PARKING_CLOSING_HOUR2 = "00:00";
	    
	    
	    private static final ParkingEntity PARKING_1 = new ParkingEntity();
	    private static final ParkingEntity PARKING_2 = new ParkingEntity();
	    
	    @Before
	    public void init() {
	    	PARKING_1.setName(PARKING_NAME1);
	    	PARKING_1.setLocation(PARKING_LICENCE_PLATE1);
	    	PARKING_1.setOpensAt(PARKING_OPENING_HOUR1);
	    	PARKING_1.setClosesAt(PARKING_CLOSING_HOUR1);

	    	PARKING_2.setName(PARKING_NAME2);
	    	PARKING_2.setLocation(PARKING_LICENCE_PLATE2);
	    	PARKING_2.setOpensAt(PARKING_OPENING_HOUR2);
	    	PARKING_2.setClosesAt(PARKING_CLOSING_HOUR2);
	    }
	    

	    @Inject
	    protected ParkingRepository parkingRepo;

	    @Test
	    @Transactional
	    public void addParking_test_success() {
	        final ParkingEntity testEntity = parkingRepo.save(PARKING_1);
	        final Optional<ParkingEntity> expectedEntity = parkingRepo.findById(testEntity.getId());
	        assertThat(expectedEntity).isNotNull();
	        assertThat(expectedEntity.get().getName()).isEqualTo(PARKING_NAME1);
	    }
	    
	    @Test
	    @Transactional
	    public void deleteParking_test_success() {
	        final ParkingEntity testEntity = parkingRepo.save(PARKING_1);
	        parkingRepo.delete(testEntity);
	        final Optional<ParkingEntity> expectedEntity = parkingRepo.findById(testEntity.getId());
	        assertThat(expectedEntity.isEmpty());
	    }
	    
	
	    
	    @Test
	    @Transactional
	    public void findAllParkings_test_success() {
	        parkingRepo.save(PARKING_1);
	        parkingRepo.save(PARKING_2);
	        final List<ParkingEntity> expectedList = parkingRepo.findAll();
	        assertThat(expectedList).isNotNull();
	        assertThat(expectedList.size()).isEqualTo(2);
	    }
	    
	    
	    @Test
	    @Transactional
	    public void updateParking_test_success() {
	        final ParkingEntity testEntity = parkingRepo.save(PARKING_1);
	        testEntity.setName(PARKING_NAME2);
	        parkingRepo.save(testEntity);
	        final Optional<ParkingEntity> expectedEntity = parkingRepo.findById(testEntity.getId());
	        assertThat(expectedEntity).isNotNull();
	        assertThat(expectedEntity.get().getName()).isEqualTo(PARKING_NAME2);
	    }
}
