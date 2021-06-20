package ro.upet.parking.system.management.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import lombok.val;
import org.junit.Before;
import org.junit.Test;

import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;


public class ParkingSpotRepositoryTest extends DataTests {


    private static final String PARKING_SPOT_NUMBER1 = "A1";
    private static final String PARKING_SPOT_NUMBER2 = "A2";

    private static final ParkingSpotEntity PARKING_SPOT_1 = new ParkingSpotEntity();
    private static final ParkingSpotEntity PARKING_SPOT_2 = new ParkingSpotEntity();

    @Inject
    private ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository parkingSpotRepo;

    @Before
    public void init() {
        PARKING_SPOT_1.setNumber(PARKING_SPOT_NUMBER1);
        PARKING_SPOT_1.setRentable(true);
        PARKING_SPOT_1.setAvailable(true);
        PARKING_SPOT_1.setRented(false);

        PARKING_SPOT_2.setNumber(PARKING_SPOT_NUMBER2);
        PARKING_SPOT_1.setRentable(false);
        PARKING_SPOT_1.setAvailable(false);
        PARKING_SPOT_1.setRented(false);
    }

    @Test
    @Transactional
    public void addParkingSpot_test_success() {
        val testEntity = parkingSpotRepo.save(PARKING_SPOT_1);
        val expectedEntity = parkingSpotRepo.findById(testEntity.getId());
        assertThat(expectedEntity).isNotNull();
        assertThat(expectedEntity.get().getNumber()).isEqualTo(PARKING_SPOT_NUMBER1);
    }

    @Test
    @Transactional
    public void deleteParkingSpot_test_success() {
        val testEntity = parkingSpotRepo.save(PARKING_SPOT_1);
        parkingSpotRepo.delete(testEntity);
        val expectedEntity = parkingSpotRepo.findById(testEntity.getId());
        assertThat(expectedEntity.isEmpty());
    }


    @Test
    @Transactional
    public void findAllParkingSpots_test_success() {
        parkingSpotRepo.save(PARKING_SPOT_1);
        parkingSpotRepo.save(PARKING_SPOT_2);
        final List<ParkingSpotEntity> expectedList = parkingSpotRepo.findAll();
        assertThat(expectedList).isNotNull();
        assertThat(expectedList.size()).isEqualTo(2);
    }


    @Test
    @Transactional
    public void updateParkingSpot_test_success() {
        val testEntity = parkingSpotRepo.save(PARKING_SPOT_1);
        testEntity.setAvailable(false);
        parkingSpotRepo.save(testEntity);
        val expectedEntity = parkingSpotRepo.findById(testEntity.getId());
        assertThat(expectedEntity).isNotNull();
        assertThat(expectedEntity.get().isAvailable()).isEqualTo(false);
    }
}

