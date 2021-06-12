package ro.upet.parking.system.management.data.impl;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;
import ro.upet.parking.system.management.data.impl.parking.zone.ParkingZoneRepository;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ITParkingZoneRepository extends ITDataTests {


    private static final String PARKING_ZONE_LETTER1 = "A";
    private static final String PARKING_ZONE_LETTER2 = "A";

    private static final ParkingZoneEntity PARKING_ZONE_1 = new ParkingZoneEntity();
    private static final ParkingZoneEntity PARKING_ZONE_2 = new ParkingZoneEntity();

    @Inject
    private ParkingZoneRepository parkingZoneRepo;

    @Before
    public void init() {
        PARKING_ZONE_1.setLetter(PARKING_ZONE_LETTER1);

        PARKING_ZONE_2.setLetter(PARKING_ZONE_LETTER2);
    }

    @Test
    public void addParkingZone_test_success() {
        final ParkingZoneEntity testEntity = parkingZoneRepo.save(PARKING_ZONE_1);
        val expectedEntity = parkingZoneRepo.findById(testEntity.getId());
        assertThat(expectedEntity).isNotNull();
        assertThat(expectedEntity.get().getLetter()).isEqualTo(PARKING_ZONE_LETTER1);
    }

    @Test
    public void deleteParkingZone_test_success() {
        val testEntity = parkingZoneRepo.save(PARKING_ZONE_1);
        parkingZoneRepo.delete(testEntity);
        val expectedEntity = parkingZoneRepo.findById(testEntity.getId());
        assertThat(expectedEntity.isEmpty());
    }


    @Test
    public void findAllParkingZones_test_success() {
        parkingZoneRepo.save(PARKING_ZONE_1);
        parkingZoneRepo.save(PARKING_ZONE_2);
        final List<ParkingZoneEntity> expectedList = parkingZoneRepo.findAll();
        assertThat(expectedList).isNotNull();
        assertThat(expectedList.size()).isEqualTo(2);
    }


    @Test
    public void updateParkingZone_test_success() {
        val testEntity = parkingZoneRepo.save(PARKING_ZONE_1);
        testEntity.setLetter(PARKING_ZONE_LETTER2);
        parkingZoneRepo.save(testEntity);
        val expectedEntity = parkingZoneRepo.findById(testEntity.getId());
        assertThat(expectedEntity).isNotNull();
        assertThat(expectedEntity.get().getLetter()).isEqualTo(PARKING_ZONE_LETTER2);
    }
}

