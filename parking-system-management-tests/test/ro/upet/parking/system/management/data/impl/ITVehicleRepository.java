package ro.upet.parking.system.management.data.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static ro.upet.parking.system.management.util.TestDataEntityBuilder.buildVehicleEntity;

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


    @Inject
    private VehicleRepository vehicleRepo;


    @Test
    public void addVehicle_test_success() {
        val testEntity = vehicleRepo.save(buildVehicleEntity());
        val expectedEntity = vehicleRepo.findById(testEntity.getId());
        assertThat(expectedEntity).isNotNull();
        assertThat(expectedEntity.get().getLicencePlate()).isEqualTo("AB10ABC");
    }


    @Test
    public void deleteVehicle_test_success() {
        val testEntity = vehicleRepo.save(buildVehicleEntity());
        vehicleRepo.delete(testEntity);
        val expectedEntity = vehicleRepo.findById(testEntity.getId());
        assertThat(expectedEntity.isEmpty());
    }


    @Test
    public void findAllVehicles_test_success() {
        val entity1 = buildVehicleEntity();
        vehicleRepo.save(entity1);
        vehicleRepo.save(buildVehicleEntity("AB11ABC", null, entity1.getUser()));
        val expectedList = vehicleRepo.findAll();
        assertThat(expectedList).isNotNull();
        assertThat(expectedList.size()).isEqualTo(2);
    }


    @Test
    public void findAllVehiclesByUsername_test_success() {
        vehicleRepo.save(buildVehicleEntity());
        val expectedList = vehicleRepo.findAllByUserUsername("Username1");
        assertThat(expectedList).isNotNull();
        assertThat(expectedList.size()).isEqualTo(1);
    }

    @Test
    public void findByLicencePlate_test_success() {
        vehicleRepo.save(buildVehicleEntity());
        val expected = vehicleRepo.findOptionalByLicencePlate("AB10ABC");
        assertThat(expected).isNotNull();
        assertThat(expected.get().getLicencePlate()).isEqualTo("AB10ABC");
    }


    @Test
    public void updateVehicle_test_success() {
        val testEntity = vehicleRepo.save(buildVehicleEntity());
        testEntity.setName("Renault1");
        vehicleRepo.save(testEntity);
        val expectedEntity = vehicleRepo.findById(testEntity.getId());
        assertThat(expectedEntity).isNotNull();
        assertThat(expectedEntity.get().getName()).isEqualTo("Renault1");
    }
}
