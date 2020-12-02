package ro.upet.parking.system.management.business.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ro.upet.parking.system.management.business.BusinessTests;
import ro.upet.parking.system.management.business.api.reservation.ReservationService;
import ro.upet.parking.system.management.business.impl.parking.spot.ParkingSpotMapper;
import ro.upet.parking.system.management.business.impl.vehicle.VehicleMapper;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.data.impl.reservation.ReservationRepository;
import ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository;
import ro.upet.parking.system.management.model.base.ReservationStatus;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;
import ro.upet.parking.system.management.model.reservation.ImtReservation;
import ro.upet.parking.system.management.model.reservation.Reservation;
import ro.upet.parking.system.management.model.vehicle.Vehicle;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReservationServiceIT extends BusinessTests {
    
    private static final String RESERVATION_LICENCE_PLATE1 = "HD00ABC";
    private static final String RESERVATION_LICENCE_PLATE2 = "HD00ABC";
    
    private static final String RESERVATION_NOTES_1 = "No notes";
    
    private static final Instant RESERVATION_START_TIME_1 = Instant.now();
    
    private static final Instant RESERVATION_END_TIME_1 = Instant.now().plusSeconds(100);
    
    @Inject
    protected ReservationRepository reservationRepo;
    
    @Inject
    protected ReservationService reservationService;
    
    @Inject
    private VehicleRepository vehicleRepo;
    
    @Inject
    private ParkingSpotRepository parkingSpotRepo;
    
    
    private static final Reservation RESERVATION_1 = ImtReservation.builder()
			.notes(RESERVATION_NOTES_1)
			.reservationStatus(ReservationStatus.EXPIRED)
			.startTime(RESERVATION_START_TIME_1)
			.endTime(RESERVATION_END_TIME_1)
			.build();
	
    
    @Test
    @Transactional
    public void addReservation_test_success() {
      	VehicleEntity ve = new VehicleEntity();	
    	ve.setLicencePlate(RESERVATION_LICENCE_PLATE1);
    	ve.setUser(new UserEntity());
    	ve = vehicleRepo.save(ve);
    	final Vehicle VEHICLE = VehicleMapper.toVehicle(ve);
    	
    	ParkingSpotEntity pse = new ParkingSpotEntity();
    	pse.setNumber("A1");
    	pse.setRentable(true);
    	pse.setAvailable(true);
    	pse.setRented(true);
    	pse = parkingSpotRepo.save(pse);
    	final ParkingSpot PARKING_SPOT = ParkingSpotMapper.toParkingSpot(pse);
        final Reservation actualResult = reservationService
        										.addReservation(ImtReservation.copyOf(RESERVATION_1)
        																			.withVehicle(VEHICLE)
        																			.withParkingSpotId(PARKING_SPOT.getId())
        																			);
        final Reservation expectedResult = ImtReservation.copyOf(RESERVATION_1)
        											.withVehicle(actualResult.getVehicle())
        											.withParkingSpotId(PARKING_SPOT.getId())
        											.withId(actualResult.getId())
        											.withCreatedAt(actualResult.getCreatedAt())
        											.withUpdatedAt(actualResult.getUpdatedAt());
        assertThat(actualResult).isNotNull();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    
    @Test
    @Transactional
    public void deleteReservationById_test_success() {
    	ReservationEntity re = new ReservationEntity();
    	re.setNotes(RESERVATION_NOTES_1);
      	
    	VehicleEntity ve = new VehicleEntity();	
    	ve.setLicencePlate(RESERVATION_LICENCE_PLATE1);
    	ve.setUser(new UserEntity());
    	ve = vehicleRepo.save(ve);
    	re.setVehicle(ve);
    	
      	ParkingSpotEntity pse = new ParkingSpotEntity();
    	pse.setNumber("A1");
    	pse.setRentable(true);
    	pse.setAvailable(true);
    	pse.setRented(true);
    	pse = parkingSpotRepo.save(pse);
    	re.setParkingSpot(pse);
    	
    	re = reservationRepo.save(re);
    	final List<Reservation> actualResult = reservationService.getReservationList();	
        reservationService.removeReservationById(re.getId());
        final List<Reservation> expectedResult = reservationService.getReservationList();
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size() - 1).isEqualTo(expectedResult.size());
    }
    
    @Test
    @Transactional
    public void findReservationById_test_success() {
    	ReservationEntity re = new ReservationEntity();
    	re.setNotes(RESERVATION_NOTES_1);
    	
    	VehicleEntity ve = new VehicleEntity();	
    	ve.setLicencePlate(RESERVATION_LICENCE_PLATE1);
    	ve.setUser(new UserEntity());
    	ve = vehicleRepo.save(ve);
    	re.setVehicle(ve);
    	
      	ParkingSpotEntity pse = new ParkingSpotEntity();
    	pse.setNumber("A1");
    	pse.setRentable(true);
    	pse.setAvailable(true);
    	pse.setRented(false);
    	pse = parkingSpotRepo.save(pse);
    	re.setParkingSpot(pse);
    	
    	re = reservationRepo.save(re);
        final Reservation actualResult = reservationService.getReservationById(re.getId());
        final Reservation expectedResult = ImtReservation.builder()
    											.id(actualResult.getId())
    											.notes(RESERVATION_NOTES_1)
    											.createdAt(actualResult.getCreatedAt())
    											.updatedAt(actualResult.getUpdatedAt())
    											.vehicle(actualResult.getVehicle())
    											.parkingSpotId(pse.getId())
    											.build();
        assertThat(actualResult).isNotNull();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    
    
    @Test
    @Transactional
    public void findAllReservations_test_success() {
    	ReservationEntity re = new ReservationEntity();
    	VehicleEntity ve = new VehicleEntity();	
    	ve.setLicencePlate(RESERVATION_LICENCE_PLATE1);
    	ve.setUser(new UserEntity());
    	ve = vehicleRepo.save(ve);
    	re.setVehicle(ve);
    	
      	ParkingSpotEntity pse = new ParkingSpotEntity();
    	pse.setNumber("A1");
    	pse.setRentable(true);
    	pse.setAvailable(true);
    	pse.setRented(false);
    	pse = parkingSpotRepo.save(pse);
    	re.setParkingSpot(pse);;
    	reservationRepo.save(re);
    	
    	re = new ReservationEntity();
    	ve = new VehicleEntity();	
    	ve.setLicencePlate(RESERVATION_LICENCE_PLATE2);
    	ve.setUser(new UserEntity());
    	ve = vehicleRepo.save(ve);
    	re.setVehicle(ve);
    	
      	pse = new ParkingSpotEntity();
    	pse.setNumber("A2");
    	pse.setRentable(false);
    	pse.setAvailable(false);
    	pse.setRented(false);
    	pse = parkingSpotRepo.save(pse);
    	re.setParkingSpot(pse);
    	reservationRepo.save(re);
    	
        final List<Reservation> actualResult = reservationService.getReservationList();	
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size()).isEqualTo(2);
    }
    
    @Test
    @Transactional
    public void updateReservation_test_success() {
       	ReservationEntity re = new ReservationEntity();
       	VehicleEntity ve = new VehicleEntity();	
       	ve.setLicencePlate(RESERVATION_LICENCE_PLATE1);
    	ve.setUser(new UserEntity());
    	ve = vehicleRepo.save(ve);
    	re.setVehicle(ve);
    	
      	ParkingSpotEntity pse = new ParkingSpotEntity();
    	pse.setNumber("A1");
    	pse.setRentable(true);
    	pse.setAvailable(true);
    	pse.setRented(false);
    	pse = parkingSpotRepo.save(pse);
    	
    	re.setParkingSpot(pse);
    	re = reservationRepo.save(re);
        final Reservation actualResult = reservationService.updateReservation(ImtReservation.copyOf(RESERVATION_1).withId(re.getId()).withParkingSpotId(pse.getId()));
        final Reservation expectedResult = ImtReservation.copyOf(RESERVATION_1)
        											.withVehicle(actualResult.getVehicle())
        											.withId(actualResult.getId())
        											.withParkingSpotId(pse.getId())
        											.withCreatedAt(actualResult.getCreatedAt())
        											.withUpdatedAt(actualResult.getUpdatedAt());
        assertThat(actualResult).isNotNull();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    
    

    
   
}
