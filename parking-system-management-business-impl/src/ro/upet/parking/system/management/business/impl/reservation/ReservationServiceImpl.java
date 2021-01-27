package ro.upet.parking.system.management.business.impl.reservation;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.reservation.ReservationService;
import ro.upet.parking.system.management.business.api.reservation.ReservationValidator;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.data.impl.reservation.ReservationRepository;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.model.base.ReservationStatus;
import ro.upet.parking.system.management.model.reservation.ImtReservationNext;
import ro.upet.parking.system.management.model.reservation.Reservation;
import ro.upet.parking.system.management.model.reservation.ReservationCreate;
import ro.upet.parking.system.management.model.reservation.ReservationNext;

/**
 * @author Andrada
 * Business level logic implementation for reservations 
 */
@Service
public class ReservationServiceImpl implements ReservationService{
	
	protected static final Logger LOGGER  = Logger.getLogger(ReservationService.class.getName());
	
	@Value("${reserversion.claim.allocated.time}")	
	private Integer RESERVATION_EXPIRATION_TIME; // 15 minutes
	
	@Inject
	private ReservationValidator reservationValidator;
	
	@Inject
	private ReservationRepository reservationRepo;
	
	@Inject
	private UserRepository userRepo;

	@Inject
	private ParkingSpotRepository parkingSpotRepo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation getById(final Long reservationId) {
		return ReservationMapper.toReservation(reservationRepo.getOne(reservationId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation getByCode(final String reservationCode) {
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Reservation> getList() {
		return ReservationMapper.toReservationList(reservationRepo.findAll());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation add(final Reservation reservation) {
		final ReservationEntity entity = ReservationMapper.toReservationEntity(reservation);
		final ReservationEntity savedEntity = reservationRepo.save(entity);
		return ReservationMapper.toReservation(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation update(final Reservation reservation) {
		final ReservationEntity entity = ReservationMapper.toReservationEntity(reservation);
		final ReservationEntity savedEntity = reservationRepo.save(entity);
		return ReservationMapper.toReservation(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation removeById(final Long reservationId) throws BusinessException {
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
	public Reservation removeByCode(final String reservationCode) {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Reservation> findAllForUserByUsername(final String username) {
		return	ReservationMapper.toReservationList(reservationRepo.findAllByUserUsername(username));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation createReservation(ReservationCreate reservationCreate) {
		// find the user for the reservation if none throw exception
		final UserEntity ue = userRepo.findByUsername(reservationCreate.getUsername())
															.orElseThrow(BusinessException::new);
		
		// find all available parking spots by parking name
		final List<ParkingSpotEntity> parkingSpots = parkingSpotRepo.findAllAvailableByParkingName(reservationCreate.getParkingName());
		final ReservationEntity re = new ReservationEntity();
		re.setUser(ue);
		re.setStartTime(Instant.parse(reservationCreate.getStartTime()));
		re.setEndTime(Instant.parse(reservationCreate.getEndTime()));
		
		// set cost as price per hour * reservation duration
		re.setCost(Duration.between(re.getEndTime(), re.getStartTime()).toHours() * parkingSpots.stream().findFirst().get().getParkingZone().getParkingLevel().getParking().getPricePerHour());
		
		// validate parking spot availability during the selected time
		re.setParkingSpot(parkingSpots.stream()
									.filter(x ->  {
										re.setParkingSpot(x);
										return x.getAvailable() && reservationValidator.validate(re);
									})
									.findFirst().orElseThrow(BusinessException :: new));

		//save reservation
		re.setReservationStatus(ReservationStatus.PENDING);
		final ReservationEntity savedEntity = reservationRepo.save(re);
		startCountdownTimer(savedEntity.getBase().getId());
		return ReservationMapper.toReservation(savedEntity);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReservationNext getReservationNext(String username) {
		
		// all upcoming reservations have the pending status
		final List<ReservationEntity> reservations = reservationRepo.findAllByReservationStatus(ReservationStatus.PENDING);
		
		// find the closest upcoming reservation 
		final Optional <ReservationEntity> re = reservations.stream().sorted((r1, r2) -> r1.getStartTime().isBefore(r2.getStartTime()) ? 1 : -1)
									.findFirst();
		
		ReservationNext reservationNext = null;
		if (re.isPresent()) {
			
			// compute times for count down timers
			long minutesTillStart = Duration.between(Instant.now(), re.get().getStartTime()).toMinutes(); 
			LocalDateTime startTimeLDT = LocalDateTime.ofInstant(re.get().getStartTime(), ZoneOffset.UTC);
			LocalDateTime currentTimeLDT = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
			LocalDateTime endTimeLDT = LocalDateTime.ofInstant(re.get().getEndTime(), ZoneOffset.UTC);
			
				
			final Integer daysTillReservation = startTimeLDT.getDayOfYear() - currentTimeLDT.getDayOfYear();
			final Integer hoursTillReservation = (int) minutesTillStart / 60;
			final Integer minutesTillReservation = (int) minutesTillStart % 60;
			
			
			final Integer reservationDurationHours = Math.abs(endTimeLDT.getHour() - startTimeLDT.getHour());
			final Integer reservationDurationMinutes = Math.abs(endTimeLDT.getMinute() - startTimeLDT.getMinute());
			
			
			reservationNext = ImtReservationNext.builder()
					.days(daysTillReservation)
					.hours(hoursTillReservation)
					.minutes(minutesTillReservation)
					.reservationId(re.get().getBase().getId())
					.durationHours(reservationDurationHours)
					.durationMinutes(reservationDurationMinutes)
					.reservationStatus(re.get().getReservationStatus())
					.build();
		}
		
		return reservationNext;
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation claim(final Long reservationId) {
		ReservationEntity re = reservationRepo.findById(reservationId).orElseThrow(BusinessException::new);
		re.setReservationStatus(ReservationStatus.CLAIMED);
		reservationRepo.save(re);
		
		//occupy spot
		ParkingSpotEntity pse = parkingSpotRepo.findById(re.getParkingSpot().getBase().getId())
				.orElseThrow(BusinessException::new);
		pse.setAvailable(false);
		parkingSpotRepo.save(pse);
		return ReservationMapper.toReservation(re);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation start(final Long reservationId) {
		ReservationEntity re = reservationRepo.findById(reservationId).orElseThrow(BusinessException::new);
		re.setReservationStatus(ReservationStatus.ONGOING);
		reservationRepo.save(re);
		
		//make spot occupied
		ParkingSpotEntity pse = parkingSpotRepo.findById(re.getParkingSpot().getBase().getId())
				.orElseThrow(BusinessException::new);
		pse.setAvailable(false);
		parkingSpotRepo.save(pse);
		return ReservationMapper.toReservation(re);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation complete(final Long reservationId) {
		ReservationEntity re = reservationRepo.findById(reservationId).orElseThrow(BusinessException::new);
		re.setReservationStatus(ReservationStatus.COMPLETED);
		reservationRepo.save(re);
		
		//make spot free
 		ParkingSpotEntity pse = parkingSpotRepo.findById(re.getParkingSpot().getBase().getId())
				.orElseThrow(BusinessException::new);
		pse.setAvailable(true);
		parkingSpotRepo.save(pse);
		return ReservationMapper.toReservation(re);
	}


	/**
	 * @param reservationId the id of the reservation
	 */
	private void startCountdownTimer(final Long reservationId) {
	    ReservationEntity re = reservationRepo.findById(reservationId)
				.orElseThrow(BusinessException::new);
		TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                	ReservationEntity re = reservationRepo.findById(reservationId)
                														.orElseThrow(BusinessException::new);
                	re.setReservationStatus(ReservationStatus.UNCLAIMED);
            		reservationRepo.save(re);
            		claimReservation(re);
				} catch (Exception e) {
					LOGGER.info(String.format("Failed to make reservation in unclaimed {%s}", e));
				}
            }
        };
        
    // schedule the status to change to unclaimed when the reservation is starting
    long duration = Math.abs(Duration.between(re.getStartTime(),
    		Instant.now()).toMillis());
    LOGGER.info(String.format("Reservation status changing to UNCALIMED in : {%d} minutes", duration / 36000));
    new Timer().scheduleAtFixedRate(timerTask, 30, duration);
	}
	
	
	/**
	 * @param reservationEntity unclaimed entity. If by the end of the timer expiration this is not claimed than remove the reservation
	 */
	private void claimReservation(final ReservationEntity reservationEntity) {
		 TimerTask timerTask = new TimerTask() {
	            @Override
	            public void run() {
	                try {
                		if (reservationEntity.getReservationStatus().equals(ReservationStatus.UNCLAIMED)) {
                			reservationRepo.delete(reservationEntity);
                		}
					} catch (Exception e) {
						LOGGER.info(String.format("Failed to remove  unclaimed reservation: {%s} ", e));
					}
	            }
	        };

	    // schedule a timer to check if the status is changed to claimed if not just remove the reservation
	    long duration = Math.abs(Duration.between(reservationEntity.getStartTime().plusSeconds(RESERVATION_EXPIRATION_TIME), 
				 reservationEntity.getStartTime()).toMillis());
	    LOGGER.info(String.format("Reservation status changing to UNCALIMED in : {%d} minutes", duration / 36000));
	    new Timer().scheduleAtFixedRate(timerTask, 30, duration);
	}



	
	
}
