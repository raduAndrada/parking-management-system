package ro.upet.parking.system.management.data.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ro.upet.parking.system.management.data.FlywayConfigurationClass;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;
import ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository;


public class ITVehicleRepository extends ITDataTests {

    private static final String VEHICLE_LICENCE_PLATE1 = "email@example.com";
    private static final String VEHICLE_LICENCE_PLATE2 = "email2@example.com";
    private static final String VEHICLE_NAME1 = "vehiclename1";
    private static final String VEHICLE_NAME2 = "vehiclename2";


    private static final VehicleEntity VEHICLE_1 = new VehicleEntity();
    private static final VehicleEntity VEHICLE_2 = new VehicleEntity();

    @Inject
    private VehicleRepository vehicleRepo;

    @Before
    public void init() {
        final UserEntity user1 = new UserEntity();
        user1.setEmail("testEmail1");
        user1.setUsername("username1");
        user1.setPassword("password1");

        final UserEntity user2 = new UserEntity();
        user2.setEmail("testEmail2");
        user2.setUsername("username2");
        user2.setPassword("password2");

        VEHICLE_1.setName(VEHICLE_NAME1);
        VEHICLE_1.setLicencePlate(VEHICLE_LICENCE_PLATE1);
        VEHICLE_1.setUser(user1);

        VEHICLE_2.setName(VEHICLE_NAME2);
        VEHICLE_2.setLicencePlate(VEHICLE_LICENCE_PLATE2);
        VEHICLE_2.setUser(user2);
    }

    @Test
    @Transactional
    public void addVehicle_test_success() {
        val testEntity = vehicleRepo.save(VEHICLE_1);
        val expectedEntity = vehicleRepo.findById(testEntity.getId());
        assertThat(expectedEntity).isNotNull();
        assertThat(expectedEntity.get().getName()).isEqualTo(VEHICLE_NAME1);
    }

    @Test
    @Transactional
    public void deleteVehicle_test_success() {
        val testEntity = vehicleRepo.save(VEHICLE_1);
        vehicleRepo.delete(testEntity);
        val expectedEntity = vehicleRepo.findById(testEntity.getId());
        assertThat(expectedEntity.isEmpty());
    }


    @Test
    @Transactional
    public void findAllVehicles_test_success() {
        vehicleRepo.save(VEHICLE_2);
        final List<VehicleEntity> expectedList = vehicleRepo.findAll();
        assertThat(expectedList).isNotNull();
        assertThat(expectedList.size()).isEqualTo(1);
    }


    @Test
    @Transactional
    public void updateVehicle_test_success() {
        val testEntity = vehicleRepo.save(VEHICLE_1);
        testEntity.setName(VEHICLE_NAME2);
        testEntity.setUser(VEHICLE_1.getUser());
        vehicleRepo.save(testEntity);
        val expectedEntity = vehicleRepo.findById(testEntity.getId());
        assertThat(expectedEntity).isNotNull();
        assertThat(expectedEntity.get().getName()).isEqualTo(VEHICLE_NAME2);
    }
}
