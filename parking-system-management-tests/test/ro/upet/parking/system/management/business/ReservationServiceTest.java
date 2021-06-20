package ro.upet.parking.system.management.business;

import com.google.common.collect.ImmutableList;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.reservation.ReservationService;
import ro.upet.parking.system.management.business.api.reservation.ReservationValidator;
import ro.upet.parking.system.management.business.reservation.ReservationMapper;
import ro.upet.parking.system.management.business.reservation.ReservationServiceImpl;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.data.impl.reservation.ReservationRepository;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.model.base.ReservationStatus;
import ro.upet.parking.system.management.model.reservation.ReservationCreate;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ro.upet.parking.system.management.util.TestDataBuilder.*;

@RunWith(SpringRunner.class)
@Profile("inttest")
public class ReservationServiceTest {
    
	private static final String START_TIME_1 = "2021-04-03T10:15:30.00Z";;
	private static final String END_TIME_1 = "2021-04-03T11:15:30.00Z";

	private static final Instant START_TIME_INSTANT = Instant.parse(START_TIME_1);
	private static final Instant END_TIME_INSTANT = Instant.parse(END_TIME_1);


	private ReservationService reservationService;

	@Mock
    protected ReservationRepository reservationRepo;

    @Mock
    private ParkingSpotRepository parkingSpotRepo;

	@Mock
	private ReservationValidator validator;

	@Mock
	private UserRepository userRepo;


	@Before
	public void setUp() {
		reservationService = new ReservationServiceImpl( validator, reservationRepo, userRepo, parkingSpotRepo);
	}


	@Test
	public void testGetById_success() {
		val entity = buildReservationEntity(START_TIME_1, END_TIME_1, ReservationStatus.PENDING);
		when(reservationRepo.getOne(any(Long.class))).thenReturn(entity);
		val actual = reservationService.getById(1L);
		val expected = ReservationMapper.toReservation(entity);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testCreateReservation_success() {
		val entity = buildReservationEntity(START_TIME_1, END_TIME_1, ReservationStatus.PENDING);
		final List<ParkingSpotEntity> parkingSpots = buildParkingSpotList();

		when(parkingSpotRepo.findAllAvailableByParkingName("Parking1")).thenReturn(parkingSpots);
		when(userRepo.findByUsername("Andrada")).thenReturn(Optional.of(buildUserEntity()));
		when(validator.isValidReservation(1L,START_TIME_INSTANT, END_TIME_INSTANT)).thenReturn(true);
		when(reservationRepo.save(any())).thenReturn(entity);
		when(reservationRepo.findById(null)).thenReturn(Optional.ofNullable(entity));

		val actual = reservationService.createReservation(buildReservationCreate());
		val expected = ReservationMapper.toReservation(entity);
		assertThat(actual).isEqualTo(expected);

		verify(parkingSpotRepo, times(1)).findAllAvailableByParkingName("Parking1");
		verify(userRepo, times(1)).findByUsername("Andrada");
		verify(reservationRepo, times(2)).save(any());
	}


	@Test(expected = BusinessException.class)
	public void testCreateReservation_failNoSpotsAreValid() {
		val entity = buildReservationEntity(START_TIME_1, END_TIME_1, ReservationStatus.PENDING);
		final List<ParkingSpotEntity> parkingSpots = buildParkingSpotList();

		when(parkingSpotRepo.findAllAvailableByParkingName("Parking1")).thenReturn(parkingSpots);
		when(userRepo.findByUsername("Andrada")).thenReturn(Optional.of(buildUserEntity()));
		entity.setParkingSpot(parkingSpots.get(0));
		when(validator.isValidReservation(1L,START_TIME_INSTANT, END_TIME_INSTANT)).thenReturn(false);
		when(reservationRepo.save(any())).thenReturn(entity);

		val actual = reservationService.createReservation(buildReservationCreate());
		val expected = ReservationMapper.toReservation(entity);
		assertThat(actual).isEqualTo(expected);

		verify(parkingSpotRepo, times(1)).findAllAvailableByParkingName("Parking1");
		verify(userRepo, times(1)).findByUsername("Andrada");
		verify(reservationRepo, times(0)).save(any());
	}


	private static List<ParkingSpotEntity> buildParkingSpotList()
	{
		return ImmutableList.of(buildParkingSpotEntity(1l),
				buildParkingSpotEntity(2l), buildParkingSpotEntity(3l));
	}

	private static ReservationCreate buildReservationCreate(){
		return ReservationCreate.builder()
				.username("Andrada")
				.startTime(START_TIME_1)
				.endTime(END_TIME_1)
				.parkingName("Parking1")
				.build();
	}
    
   
}
