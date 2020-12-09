package ro.upet.parking.system.management.business.impl.reservation;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.reservation.ReservationService;
import ro.upet.parking.system.management.business.api.reservation.ReservationValidator;
import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.impl.parking.level.ParkingLevelRepository;
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
	
	@Value("${reserversion.claim.allocated.time}")	
	private Integer RESERVATION_EXPIRATION_TIME; // 15 minutes
	
	@Inject
	private ReservationValidator reservationValidator;
	
	@Inject
	private ReservationRepository reservationRepo;
	
	@Inject
	private UserRepository userRepo;

	
	@Inject
	private ParkingLevelRepository parkingLevelRepo;

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
		// TODO Auto-generated method stub
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
		entity.setCreatedAt(Instant.now());
		entity.setUpdatedAt(Instant.now());
		final ReservationEntity savedEntity = reservationRepo.save(entity);
		return ReservationMapper.toReservation(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation update(final Reservation reservation) {
		final ReservationEntity entity = ReservationMapper.toReservationEntity(reservation);
		entity.setUpdatedAt(Instant.now());
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
		final UserEntity ue = userRepo.findByUsername(reservationCreate.getUsername())
															.orElseThrow(BusinessException::new);
		final List<ParkingSpotEntity> parkingSpots = Lists.newArrayList();//parkingSpotRepo.findAllAvailableByParkingName(reservationCreate.getParkingName());
		final List<ParkingLevelEntity> parkingLevels = parkingLevelRepo.findAllByParkingName(reservationCreate.getParkingName());
		parkingLevels.stream().forEach(pl -> {
			pl.getParkingZones().forEach(pz -> {
				pz.setParkingLevel(pl);
				pz.getParkingSpots().stream().forEach(ps -> ps.setParkingZone(pz));
				parkingSpots.addAll(pz.getParkingSpots());
			});
		});
		
		final ReservationEntity re = new ReservationEntity();
		re.setUser(ue);
		re.setStartTime(Instant.parse(reservationCreate.getStartTime()));
		re.setEndTime(Instant.parse(reservationCreate.getEndTime()));	
		re.setParkingSpot(parkingSpots.stream()
									.filter(x ->  {
										re.setParkingSpot(x);
										return x.getAvailable() && reservationValidator.validate(re);
									})
									.findFirst().orElseThrow(BusinessException :: new));

		re.setCreatedAt(Instant.now());
		re.setUpdatedAt(Instant.now());
		re.setReservationStatus(ReservationStatus.PENDING);
		final ReservationEntity savedEntity = reservationRepo.save(re);
		startCountdownTimer(re.getId());
		return ReservationMapper.toReservation(savedEntity);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReservationNext getReservationNext(String username) {
		final List<ReservationEntity> reservations = reservationRepo.findAllByReservationStatus(ReservationStatus.PENDING);
		final Optional <ReservationEntity> re = reservations.stream().sorted((r1, r2) -> r1.getStartTime().isBefore(r2.getStartTime()) ? 1 : -1)
									.findFirst();
		ReservationNext reservationNext = null;
		if (re.isPresent()) {
			LocalDateTime startTimeLDT = LocalDateTime.ofInstant(re.get().getStartTime(), ZoneOffset.UTC);
			LocalDateTime currentTimeLDT = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
		
			final Integer daysTillReservation = startTimeLDT.getDayOfYear() - currentTimeLDT.getDayOfYear();
			final Integer hoursTillReservation = Math.abs(startTimeLDT.getHour() - currentTimeLDT.getHour());
			final Integer minutesTillReservation = Math.abs(startTimeLDT.getMinute() - currentTimeLDT.getMinute());
			
			
			reservationNext = ImtReservationNext.builder()
					.days(daysTillReservation)
					.hours(hoursTillReservation)
					.minutes(minutesTillReservation)
					.reservationId(re.get().getId())
					.reservationStatus(re.get().getReservationStatus())
					.build();
		}
		
		return reservationNext;
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation claim(Long reservationId) {
		ReservationEntity re = reservationRepo.findById(reservationId).orElseThrow(BusinessException::new);
		re.setReservationStatus(ReservationStatus.CLAIMED);
		re.setUpdatedAt(Instant.now());
		reservationRepo.save(re);
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
					e.printStackTrace();
				}
            }
        };

    Timer timer = new Timer();
    // the timer is scheduled to start in the future
    long duration = Math.abs(Duration.between(re.getStartTime(),
    		Instant.now()).toMillis());
    timer.scheduleAtFixedRate(timerTask, 30, duration);
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
						e.printStackTrace();
					}
	            }
	        };

	    Timer timer = new Timer();//create a new Timer
	    timer.scheduleAtFixedRate(timerTask, 30,
	    		 Math.abs(Duration.between(reservationEntity.getStartTime().plusSeconds(RESERVATION_EXPIRATION_TIME), 
	    				 reservationEntity.getStartTime()).toMillis()));
	}



	
	
}
