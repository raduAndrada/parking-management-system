package ro.upet.parking.system.management.business;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.vehicle.VehicleService;
import ro.upet.parking.system.management.business.api.vehicle.VehicleValidator;
import ro.upet.parking.system.management.business.vehicle.VehicleMapper;
import ro.upet.parking.system.management.business.vehicle.VehicleServiceImpl;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;
import ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ro.upet.parking.system.management.util.TestDataBuilder.buildVehicle;
import static ro.upet.parking.system.management.util.TestDataBuilder.buildVehicleEntity;

@RunWith(SpringRunner.class)
public class VehicleServiceTest {

	private static final String VALID_LICENCE_PLATE_1 = "XX10YYY";

	@Mock
	private VehicleValidator validator;

	@Mock
	private VehicleRepository vehicleRepo;

	private VehicleService vehicleService;

	@Before
	public void setUp(){
		vehicleService = new VehicleServiceImpl(vehicleRepo, validator);
	}

	@Test
	public void testAdd_success(){
		val entity = buildVehicleEntity(VALID_LICENCE_PLATE_1);
		doNothing().when(validator).validate(VALID_LICENCE_PLATE_1);
		when(vehicleRepo.save(any(VehicleEntity.class))).thenReturn(entity);

		val actual = vehicleService.add(buildVehicle(VALID_LICENCE_PLATE_1));
		val expected = VehicleMapper.toVehicle(entity);

		assertThat(actual).isEqualTo(expected);
		verify(validator, times(1)).validate(VALID_LICENCE_PLATE_1);
		verify(vehicleRepo, times(1)).save(any(VehicleEntity.class));
	}

	@Test(expected = BusinessException.class)
	public void testAdd_fail(){
		val entity = buildVehicleEntity(VALID_LICENCE_PLATE_1);
		doThrow(BusinessException.class).when(validator).validate(VALID_LICENCE_PLATE_1);

		vehicleService.add(buildVehicle(VALID_LICENCE_PLATE_1));

		verify(validator, times(1)).validate(VALID_LICENCE_PLATE_1);
		verifyNoInteractions(vehicleRepo);
	}


}
