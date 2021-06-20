package ro.upet.parking.system.management.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.inject.Inject;

import lombok.val;
import org.junit.Before;
import org.junit.Test;

import ro.upet.parking.system.management.data.api.parking.ParkingEntity;
import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;
import ro.upet.parking.system.management.data.impl.parking.ParkingRepository;

public class ParkingLevelRepositoryTest extends DataTests {


    private static final String PARKING_LEVEL_NUMBER1 = "0";
    private static final String PARKING_LEVEL_NUMBER2 = "1";

    private static final ParkingLevelEntity PARKING_LEVEL_1 = new ParkingLevelEntity();
    private static final ParkingLevelEntity PARKING_LEVEL_2 = new ParkingLevelEntity();

    @Inject
    private ro.upet.parking.system.management.data.impl.parking.level.ParkingLevelRepository parkingLevelRepo;

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
    public void addParkingLevel_test_success() {
        val testEntity = parkingLevelRepo.save(PARKING_LEVEL_1);
        val expectedEntity = parkingLevelRepo.findById(testEntity.getId());
        assertThat(expectedEntity).isNotNull();
        assertThat(expectedEntity.get().getNumber()).isEqualTo(PARKING_LEVEL_NUMBER1);
    }

    @Test
    public void deleteParkingLevel_test_success() {
        val testEntity = parkingLevelRepo.save(PARKING_LEVEL_1);
        parkingLevelRepo.delete(testEntity);
        val expectedEntity = parkingLevelRepo.findById(testEntity.getId());
        assertThat(expectedEntity.isEmpty());
    }


    @Test
    public void findAllParkingLevels_test_success() {
        parkingLevelRepo.save(PARKING_LEVEL_1);
        final List<ParkingLevelEntity> expectedList = parkingLevelRepo.findAll();
        assertThat(expectedList).isNotNull();
        assertThat(expectedList.size()).isEqualTo(1);
    }


    @Test
    public void updateParkingLevel_test_success() {
        val testEntity = parkingLevelRepo.save(PARKING_LEVEL_1);
        testEntity.setNumber(PARKING_LEVEL_NUMBER2);
        parkingLevelRepo.save(testEntity);
        val expectedEntity = parkingLevelRepo.findById(testEntity.getId());
        assertThat(expectedEntity).isNotNull();
        assertThat(expectedEntity.get().getNumber()).isEqualTo(PARKING_LEVEL_NUMBER2);
    }
}

