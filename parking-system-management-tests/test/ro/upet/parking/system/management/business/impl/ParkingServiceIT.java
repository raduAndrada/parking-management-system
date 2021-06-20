package ro.upet.parking.system.management.business.impl;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ParkingServiceIT  {
    
//    @Inject
//    protected ParkingRepository parkingRepo;
//
//    @Inject
//    protected ParkingService parkingService;
//
//
//    private static final Parking PARKING_1 = ImtParking.builder()
//    		.name("Test parking 1")
//    		.opensAt("7:00")
//    		.closesAt("22:00")
//    		.location("Test location 1")
//			.build();
//
//
//    @Test
//    @Transactional
//    public void addParking_test_success() {
//        final Parking actualResult = parkingService.add(PARKING_1);
//        final Parking expectedResult = ImtParking.copyOf(PARKING_1)
//        											.withId(actualResult.getId())
//        											.withCode(actualResult.getCode())
//        											.withCreatedAt(actualResult.getCreatedAt())
//        											.withUpdatedAt(actualResult.getUpdatedAt());
//        assertThat(actualResult).isNotNull();
//        assertThat(actualResult).isEqualTo(expectedResult);
//    }
//
//    @Test
//    @Transactional
//    public void deleteParkingById_test_success() {
//    	ParkingEntity pe = new ParkingEntity();
//    	pe.setName("Test Parking 1");
//    	pe = parkingRepo.save(pe);
//
//    	final List<Parking> actualResult = parkingService.getList();
//        parkingService.removeById(pe.getId());
//        final List<Parking> expectedResult = parkingService.getList();
//        assertThat(actualResult).isNotNull();
//        assertThat(actualResult.size() - 1).isEqualTo(expectedResult.size());
//    }
//
//    @Test
//    @Transactional
//    public void findParkingById_test_success() {
//    	ParkingEntity pe = new ParkingEntity();
//    	pe.setName("Test Parking 1");
//    	pe = parkingRepo.save(pe);
//        final Parking actualResult = parkingService.getById(pe.getId());
//        final Parking expectedResult = ImtParking.builder()
//    											.id(actualResult.getId())
//    											.createdAt(actualResult.getCreatedAt())
//    											.updatedAt(actualResult.getUpdatedAt())
//    											.name("Test Parking 1")
//    											.build();
//        assertThat(actualResult).isNotNull();
//        assertThat(actualResult).isEqualTo(expectedResult);
//    }
//
//
//    @Test
//    @Transactional
//    public void findAllParkings_test_success() {
//    	ParkingEntity pe = new ParkingEntity();
//    	parkingRepo.save(pe);
//
//    	pe = new ParkingEntity();
//    	parkingRepo.save(pe);
//
//        final List<Parking> actualResult = parkingService.getList();
//        assertThat(actualResult).isNotNull();
//        assertThat(actualResult.size()).isEqualTo(2);
//    }
//
//    @Test
//    @Transactional
//    public void updateParking_test_success() {
//       	ParkingEntity pe = new ParkingEntity();
//    	pe = parkingRepo.save(pe);
//        final Parking actualResult = parkingService.update(ImtParking.copyOf(PARKING_1).withId(pe.getId()));
//        final Parking expectedResult = ImtParking.copyOf(PARKING_1)
//        											.withId(actualResult.getId())
//        											.withCreatedAt(actualResult.getCreatedAt())
//        											.withUpdatedAt(actualResult.getUpdatedAt());
//        assertThat(actualResult).isNotNull();
//        assertThat(actualResult).isEqualTo(expectedResult);
//    }
//
//
//
//
   
}
