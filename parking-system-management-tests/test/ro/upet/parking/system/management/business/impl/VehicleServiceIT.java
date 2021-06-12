package ro.upet.parking.system.management.business.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.vehicle.VehicleService;
import ro.upet.parking.system.management.business.api.vehicle.VehicleValidator;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;
import ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository;
import ro.upet.parking.system.management.model.base.Size;
import ro.upet.parking.system.management.model.user.ImtUser;
import ro.upet.parking.system.management.model.user.User;
import ro.upet.parking.system.management.model.vehicle.ImtVehicle;
import ro.upet.parking.system.management.model.vehicle.Vehicle;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleServiceIT {
	private static final String VEHICLE_NAME1 = "name 1";
	private static final String VEHICLE_NAME2 = "name 2";

	private static final String VEHICLE_LICENCE_PLATE1 = "HD00ABC";

	@Inject
	protected VehicleRepository vehicleRepo;

	@Inject
	protected VehicleValidator vehicleValidator;

	@Inject
	protected VehicleService vehicleService;

	private static final User USER = ImtUser.builder().name("Andrada").username("Andrada").email("email").build();

	private static final Vehicle VEHICLE_1 = ImtVehicle.builder().name(VEHICLE_NAME1)
			.licencePlate(VEHICLE_LICENCE_PLATE1).size(Size.MEDIUM).user(USER).build();

	@Test
	@Transactional
	public void addVehicle_test_success() {
		final Vehicle actualResult = vehicleService.add(VEHICLE_1);
		final Vehicle expectedResult = ImtVehicle.copyOf(VEHICLE_1).withCode(actualResult.getCode())
				.withUser(actualResult.getUser()).withId(actualResult.getId())
				.withCreatedAt(actualResult.getCreatedAt()).withUpdatedAt(actualResult.getUpdatedAt());
		assertThat(actualResult).isNotNull();
		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test(expected = BusinessException.class)
	@Transactional
	public void addVehicle_test_fail_licencePlateTaken() {
		vehicleService.add(VEHICLE_1);
		vehicleService.add(VEHICLE_1);
	}

	@Test
	@Transactional
	public void deleteVehicleById_test_success() {
		VehicleEntity ve = new VehicleEntity();
		UserEntity ue = new UserEntity();
		ve.setUser(ue);
		ve.setName(VEHICLE_NAME1);
		ve = vehicleRepo.save(ve);
		final List<Vehicle> actualResult = vehicleService.getList();
		vehicleService.removeById(ve.getId());
		final List<Vehicle> expectedResult = vehicleService.getList();
		assertThat(actualResult).isNotNull();
		assertThat(actualResult.size() - 1).isEqualTo(expectedResult.size());
	}

	@Test
	@Transactional
	public void findVehicleById_test_success() {
		VehicleEntity ve = new VehicleEntity();
		ve.setName(VEHICLE_NAME1);
		UserEntity ue = new UserEntity();
		ve.setUser(ue);
		ve = vehicleRepo.save(ve);
		final Vehicle actualResult = vehicleService.getById(ve.getId());
		final Vehicle expectedResult = ImtVehicle.builder().id(actualResult.getId()).name(VEHICLE_NAME1)
				.createdAt(actualResult.getCreatedAt()).updatedAt(actualResult.getUpdatedAt())
				.user(actualResult.getUser()).build();
		assertThat(actualResult).isNotNull();
		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	@Transactional
	public void findAllVehicles_test_success() {
		VehicleEntity ve = new VehicleEntity();
		ve.setName(VEHICLE_NAME1);
		UserEntity ue = new UserEntity();
		ve.setUser(ue);
		vehicleRepo.save(ve);

		ve = new VehicleEntity();
		ve.setName(VEHICLE_NAME2);
		ve.setUser(ue);
		vehicleRepo.save(ve);

		final List<Vehicle> actualResult = vehicleService.getList();
		assertThat(actualResult).isNotNull();
		assertThat(actualResult.size()).isEqualTo(2);
	}

	@Test
	@Transactional
	public void updateVehicle_test_success() {
		VehicleEntity ve = new VehicleEntity();
		UserEntity ue = new UserEntity();
		ve.setUser(ue);
		ve.setName(VEHICLE_NAME1);
		ve = vehicleRepo.save(ve);
		final Vehicle actualResult = vehicleService.update(ImtVehicle.copyOf(VEHICLE_1).withId(ve.getId()));
		final Vehicle expectedResult = ImtVehicle.copyOf(VEHICLE_1).withUser(actualResult.getUser())
				.withId(actualResult.getId()).withCreatedAt(actualResult.getCreatedAt())
				.withUpdatedAt(actualResult.getUpdatedAt());
		assertThat(actualResult).isNotNull();
		assertThat(actualResult).isEqualTo(expectedResult);
	}

}
