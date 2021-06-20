package ro.upet.parking.system.management.business;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import ro.upet.parking.system.management.business.api.parking.ParkingService;
import ro.upet.parking.system.management.business.parking.ParkingServiceImpl;
import ro.upet.parking.system.management.data.api.parking.ParkingEntity;
import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;
import ro.upet.parking.system.management.data.impl.parking.ParkingRepository;
import ro.upet.parking.system.management.data.impl.parking.level.ParkingLevelRepository;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.data.impl.parking.zone.ParkingZoneRepository;
import ro.upet.parking.system.management.model.parking.Parking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ro.upet.parking.system.management.business.parking.ParkingMapper.toParking;
import static ro.upet.parking.system.management.util.TestDataBuilder.*;

@RunWith(SpringRunner.class)
public class ParkingServiceTest {

    @Mock
    private ParkingRepository parkingRepo;

    @Mock
    private ParkingLevelRepository parkingLevelRepo;

    @Mock
    private ParkingZoneRepository parkingZoneRepo;

    @Mock
    private ParkingSpotRepository parkingSpotRepo;

    ParkingService parkingService;

    @Before
    public void setUp() {
        parkingService = new ParkingServiceImpl(parkingRepo, parkingLevelRepo, parkingZoneRepo, parkingSpotRepo);
    }

    @Test
    public void testAdd_success() {
        final Parking parking = buildParking();
        final ParkingEntity entity = buildParkingEntity();
        when(parkingRepo.save(any(ParkingEntity.class))).thenReturn(entity);

        val actual = parkingService.add(parking);
        val expected = toParking(entity);

        assertThat(actual).isEqualTo(expected);
        verify(parkingRepo, Mockito.times(1)).save(any(ParkingEntity.class));
    }

    @Test
    public void testConfigureParking_success() {
        val entity = buildParkingEntity();
        val parkingSpotEntity = buildParkingSpotEntity(0L);
        val parkingZoneEntity = buildParkingZoneEntity( );
        val parkingLevelEntity = buildParkingLevelEntity( );

        when(parkingRepo.save(any(ParkingEntity.class))).thenReturn(entity);
        when(parkingSpotRepo.save(any(ParkingSpotEntity.class))).thenReturn(parkingSpotEntity);
        when(parkingZoneRepo.save(any(ParkingZoneEntity.class))).thenReturn(parkingZoneEntity);
        when(parkingLevelRepo.save(any(ParkingLevelEntity.class))).thenReturn(parkingLevelEntity);

        parkingService.configureParking(buildParkingCreate('a', 'b', 1,2));
        verify(parkingRepo, Mockito.times(1)).save(any(ParkingEntity.class));
        verify(parkingLevelRepo, Mockito.times(1)).save(any(ParkingLevelEntity.class));
        verify(parkingZoneRepo, Mockito.times(2)).save(any(ParkingZoneEntity.class));
        verify(parkingSpotRepo, Mockito.times(4)).save(any(ParkingSpotEntity.class));
    }

    @Test
    public void testConfigureParkingMultiple_success() {
        val entity = buildParkingEntity();
        val parkingSpotEntity = buildParkingSpotEntity(0L);
        val parkingZoneEntity = buildParkingZoneEntity( );
        val parkingLevelEntity = buildParkingLevelEntity( );

        when(parkingRepo.save(any(ParkingEntity.class))).thenReturn(entity);
        when(parkingSpotRepo.save(any(ParkingSpotEntity.class))).thenReturn(parkingSpotEntity);
        when(parkingZoneRepo.save(any(ParkingZoneEntity.class))).thenReturn(parkingZoneEntity);
        when(parkingLevelRepo.save(any(ParkingLevelEntity.class))).thenReturn(parkingLevelEntity);

        parkingService.configureParking(buildParkingCreate('a', 'g', 3,5));
        verify(parkingRepo, Mockito.times(1)).save(any(ParkingEntity.class));
        verify(parkingLevelRepo, Mockito.times(3)).save(any(ParkingLevelEntity.class));
        verify(parkingZoneRepo, Mockito.times(21)).save(any(ParkingZoneEntity.class));
        verify(parkingSpotRepo, Mockito.times(105)).save(any(ParkingSpotEntity.class));
    }






}
