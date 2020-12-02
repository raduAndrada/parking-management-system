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
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;
import ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleRepositoryIT extends DataTests {
	
		private static final String VEHICLE_LICENCE_PLATE1 = "email@example.com";
	    private static final String VEHICLE_LICENCE_PLATE2 = "email2@example.com";
	    private static final String VEHICLE_NAME1 = "vehiclename1";
	    private static final String VEHICLE_NAME2 = "vehiclename2";
	    
	    
	    private static final VehicleEntity VEHICLE_1 = new VehicleEntity();
	    private static final VehicleEntity VEHICLE_2 = new VehicleEntity();
	    
	    @Before
	    public void init() {
	    	VEHICLE_1.setName(VEHICLE_NAME1);
	    	VEHICLE_1.setLicencePlate(VEHICLE_LICENCE_PLATE1);
	    	VEHICLE_1.setUser(new UserEntity());

	    	VEHICLE_2.setName(VEHICLE_NAME2);
	    	VEHICLE_2.setLicencePlate(VEHICLE_LICENCE_PLATE2);
	    	VEHICLE_2.setUser(new UserEntity());
	    }
	    

	    @Inject
	    protected VehicleRepository vehicleRepo;

	    @Test
	    @Transactional
	    public void addVehicle_test_success() {
	        final VehicleEntity testEntity = vehicleRepo.save(VEHICLE_1);
	        final Optional<VehicleEntity> expectedEntity = vehicleRepo.findById(testEntity.getId());
	        assertThat(expectedEntity).isNotNull();
	        assertThat(expectedEntity.get().getName()).isEqualTo(VEHICLE_NAME1);
	    }
	    
	    @Test
	    @Transactional
	    public void deleteVehicle_test_success() {
	        final VehicleEntity testEntity = vehicleRepo.save(VEHICLE_1);
	        vehicleRepo.delete(testEntity);
	        final Optional<VehicleEntity> expectedEntity = vehicleRepo.findById(testEntity.getId());
	        assertThat(expectedEntity.isEmpty());
	    }
	    
	
	    
	    @Test
	    @Transactional
	    public void findAllVehicles_test_success() {
	        vehicleRepo.save(VEHICLE_1);
	        vehicleRepo.save(VEHICLE_2);
	        final List<VehicleEntity> expectedList = vehicleRepo.findAll();
	        assertThat(expectedList).isNotNull();
	        assertThat(expectedList.size()).isEqualTo(2);
	    }
	    
	    
	    @Test
	    @Transactional
	    public void updateVehicle_test_success() {
	        final VehicleEntity testEntity = vehicleRepo.save(VEHICLE_1);
	        testEntity.setName(VEHICLE_NAME2);
	        vehicleRepo.save(testEntity);
	        final Optional<VehicleEntity> expectedEntity = vehicleRepo.findById(testEntity.getId());
	        assertThat(expectedEntity).isNotNull();
	        assertThat(expectedEntity.get().getName()).isEqualTo(VEHICLE_NAME2);
	    }
}
