package ro.upet.parking.system.management.business.impl.reservation;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ro.upet.parking.system.management.business.api.reservation.ReservationService;
import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.data.impl.reservation.ReservationRepository;
import ro.upet.parking.system.management.model.reservation.Reservation;

/**
 * @author Andrada
 * Business level logic implementation for reservations 
 */
@Service
public class ReservationServiceImpl implements ReservationService{
	
	@Inject
	ReservationRepository reservationRepo;
	
	@Inject
	ParkingSpotRepository parkingSpotRepo;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation getReservationById(final Long reservationId) {
		return ReservationMapper.toReservation(reservationRepo.getOne(reservationId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation getReservationByCode(final String reservationCode) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Reservation> getReservationList() {
		return ReservationMapper.toReservationList(reservationRepo.findAll());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation addReservation(final Reservation reservation) {
		final ReservationEntity entity = ReservationMapper.toReservationEntity(reservation);
		entity.setParkingSpot(parkingSpotRepo.getOne(reservation.getParkingSpotId()));
		entity.setCreatedAt(Instant.now());
		entity.setUpdatedAt(Instant.now());
		final ReservationEntity savedEntity = reservationRepo.save(entity);
		return ReservationMapper.toReservation(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation updateReservation(final Reservation reservation) {
		final ReservationEntity entity = ReservationMapper.toReservationEntity(reservation);
		entity.setParkingSpot(parkingSpotRepo.getOne(reservation.getParkingSpotId()));
		entity.setUpdatedAt(Instant.now());
		final ReservationEntity savedEntity = reservationRepo.save(entity);
		return ReservationMapper.toReservation(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation removeReservationById(final Long reservationId) throws Exception {
		final ReservationEntity entity = reservationRepo.getOne(reservationId);
		if (entity == null ) {
			throw new Exception();
		}
		reservationRepo.deleteById(reservationId);
		return ReservationMapper.toReservation(entity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation removeReservationByCode(final String reservationCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
