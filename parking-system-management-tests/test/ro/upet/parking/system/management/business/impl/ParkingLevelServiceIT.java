package ro.upet.parking.system.management.business.impl;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ParkingLevelServiceIT {

//	@Inject
//	protected ParkingLevelRepository parkingLevelRepo;
//
//	@Inject
//	protected ParkingLevelService parkingLevelService;
//
//	@Inject
//	protected ParkingRepository parkingRepo;
//
//	private static final ParkingLevel PARKING_LEVEL_1 = ImtParkingLevel.builder().number("1").build();
//
//	@Test
//	@Transactional
//	public void addParkingLevel_test_success() {
//		ParkingEntity pe = new ParkingEntity();
//		pe.setName("Test Parking");
//		pe.setOpensAt("07:00");
//		pe.setClosesAt("17:00");
//		pe.setBase(new BaseEntity());
//		pe = parkingRepo.save(pe);
//		final Parking p = ParkingMapper.toParking(pe);
//
//		final ParkingLevel actualResult = parkingLevelService
//				.add(ImtParkingLevel.copyOf(PARKING_LEVEL_1).withParking(p));
//		final ParkingLevel expectedResult = ImtParkingLevel.copyOf(PARKING_LEVEL_1)
//				.withParking(p)
//				.withCode(actualResult.getCode())
//				.withId(actualResult.getId()).withCreatedAt(actualResult.getCreatedAt())
//				.withUpdatedAt(actualResult.getUpdatedAt()).withParking(p);
//
//		assertThat(actualResult).isNotNull();
//		assertThat(actualResult).isEqualTo(expectedResult);
//	}
//
//	@Test
//	@Transactional
//	public void deleteParkingLevelById_test_success() {
//		ParkingLevelEntity ple = new ParkingLevelEntity();
//		ParkingEntity pe = new ParkingEntity();
//		pe.setName("Test Parking");
//		pe = parkingRepo.save(pe);
//		ple.setParking(pe);
//		ple.setNumber("1");
//		ple = parkingLevelRepo.save(ple);
//
//		final List<ParkingLevel> actualResult = parkingLevelService.getList();
//		parkingLevelService.removeById(ple.getId());
//		final List<ParkingLevel> expectedResult = parkingLevelService.getList();
//		assertThat(actualResult).isNotNull();
//		assertThat(actualResult.size() - 1).isEqualTo(expectedResult.size());
//	}
//
//	@Test
//	@Transactional
//	public void findParkingLevelById_test_success() {
//		ParkingLevelEntity ple = new ParkingLevelEntity();
//		ParkingEntity pe = new ParkingEntity();
//		pe.setName("Test Parking");
//		pe = parkingRepo.save(pe);
//		ple.setParking(pe);
//		ple.setNumber("1");
//		ple = parkingLevelRepo.save(ple);
//		final ParkingLevel actualResult = parkingLevelService.getById(ple.getId());
//		final ParkingLevel expectedResult = ImtParkingLevel.builder().id(actualResult.getId())
//				.createdAt(actualResult.getCreatedAt()).updatedAt(actualResult.getUpdatedAt()).number("1")
//				.parking(ParkingMapper.toParking(pe)).build();
//		assertThat(actualResult).isNotNull();
//		assertThat(actualResult).isEqualTo(expectedResult);
//	}
//
//	@Test
//	@Transactional
//	public void findAllParkingLevels_test_success() {
//		ParkingEntity pe = new ParkingEntity();
//		pe.setName("Test Parking");
//		pe = parkingRepo.save(pe);
//
//		ParkingLevelEntity ple = new ParkingLevelEntity();
//		ple.setParking(pe);
//		parkingLevelRepo.save(ple);
//
//		ple = new ParkingLevelEntity();
//		ple.setParking(pe);
//		parkingLevelRepo.save(ple);
//
//		final List<ParkingLevel> actualResult = parkingLevelService.getList();
//		assertThat(actualResult).isNotNull();
//		assertThat(actualResult.size()).isEqualTo(2);
//	}
//
//	@Test
//	@Transactional
//	public void updateParkingLevel_test_success() {
//		ParkingLevelEntity ple = new ParkingLevelEntity();
//		ParkingEntity pe = new ParkingEntity();
//		pe.setName("Test Parking");
//		pe = parkingRepo.save(pe);
//		ple = parkingLevelRepo.save(ple);
//		ple.setParking(pe);
//		final Parking p = ParkingMapper.toParking(pe);
//		final ParkingLevel actualResult = parkingLevelService
//				.update(ImtParkingLevel.copyOf(PARKING_LEVEL_1).withId(ple.getId()).withParking(p));
//		final ParkingLevel expectedResult = ImtParkingLevel.copyOf(PARKING_LEVEL_1).withId(actualResult.getId())
//				.withCreatedAt(actualResult.getCreatedAt()).withUpdatedAt(actualResult.getUpdatedAt()).withParking(p);
//		assertThat(actualResult).isNotNull();
//		assertThat(actualResult).isEqualTo(expectedResult);
//	}

}
