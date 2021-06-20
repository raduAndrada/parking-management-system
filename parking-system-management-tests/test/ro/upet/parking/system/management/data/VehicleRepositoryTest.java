package ro.upet.parking.system.management.data;

import lombok.val;
import org.junit.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static ro.upet.parking.system.management.util.TestDataBuilder.buildVehicleEntity;


public class VehicleRepositoryTest extends DataTests {


    @Inject
    private ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository vehicleRepo;


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
        val expectedList = vehicleRepo.findAllByUserUsername("Andrada");
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
