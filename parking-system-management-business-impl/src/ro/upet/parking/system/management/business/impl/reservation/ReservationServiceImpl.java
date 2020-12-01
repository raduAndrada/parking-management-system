package ro.upet.parking.system.management.business.impl.reservation;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.reservation.ReservationService;
import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.data.impl.reservation.ReservationRepository;
import ro.upet.parking.system.management.model.base.ReservationStatus;
import ro.upet.parking.system.management.model.reservation.Reservation;

/**
 * @author Andrada
 * Business level logic implementation for reservations 
 */
@Service
public class ReservationServiceImpl implements ReservationService{
	
	public static final Integer RESERVATION_EXPIRATION_TIME = 60 * 15; // 15 minutes
	
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
	public Reservation removeReservationById(final Long reservationId) throws BusinessException {
		final ReservationEntity entity = reservationRepo.getOne(reservationId);
		if (entity == null ) {
			throw new BusinessException("Reservation does not exist");
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation reserveSpot(final Reservation reservation) {
		final Reservation savedReservation = addReservation(reservation);
		 TimerTask timerTask = new TimerTask() {

	            @Override
	            public void run() {
	                try {
	                	Optional<ReservationEntity> re = reservationRepo.findById(savedReservation.getId());
	                	if (re.isPresent()) {
	                		if (re.get().getReservationStatus().equals(ReservationStatus.UNCLAIMED)) {
	                			removeReservationById(re.get().getId());
	                		}
	                	}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }
	        };

	    Timer timer = new Timer("MyTimer");//create a new Timer
	    timer.scheduleAtFixedRate(timerTask, 30, Duration.between(reservation.getStartTime().plusSeconds(RESERVATION_EXPIRATION_TIME), reservation.getStartTime()).toMillis());
		return null;
	}
	
	
}
