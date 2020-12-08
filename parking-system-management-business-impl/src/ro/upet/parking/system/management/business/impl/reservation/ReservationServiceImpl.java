package ro.upet.parking.system.management.business.impl.reservation;

import java.time.Duration;
import java.time.Instant;
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
import ro.upet.parking.system.management.business.impl.vehicle.VehicleMapper;
import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;
import ro.upet.parking.system.management.data.impl.parking.level.ParkingLevelRepository;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.data.impl.reservation.ReservationRepository;
import ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository;
import ro.upet.parking.system.management.model.base.ReservationStatus;
import ro.upet.parking.system.management.model.reservation.Reservation;
import ro.upet.parking.system.management.model.reservation.ReservationCreate;
import ro.upet.parking.system.management.model.vehicle.Vehicle;

/**
 * @author Andrada
 * Business level logic implementation for reservations 
 */
@Service
public class ReservationServiceImpl implements ReservationService{
	
	@Value("${reserversion.claim.allocated.time}")	
	private Integer RESERVATION_EXPIRATION_TIME; // 15 minutes
	
	@Inject
	private ReservationRepository reservationRepo;
	
	@Inject
	private VehicleRepository vehicleRepo;
	
	@Inject
	private ParkingSpotRepository parkingSpotRepo;
	
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
	public Reservation reserveSpot(final Reservation reservation) {
		final Reservation savedReservation = add(reservation);
		 TimerTask timerTask = new TimerTask() {

	            @Override
	            public void run() {
	                try {
	                	Optional<ReservationEntity> re = reservationRepo.findById(savedReservation.getId());
	                	if (re.isPresent()) {
	                		if (re.get().getReservationStatus().equals(ReservationStatus.UNCLAIMED)) {
	                			removeById(re.get().getId());
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Reservation> findAllForUserByUsername(final String username) {
		final List<Vehicle> vehicles = VehicleMapper.toVehicleList(vehicleRepo.findAllByUserUsename(username));
		final List <Reservation> resevations = Lists.newArrayList(); 
		vehicles.stream().forEach(v -> resevations.addAll(ReservationMapper
												.toReservationList(reservationRepo.findAllByVehicleId(v.getId()))));
		return	resevations;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Reservation createReservation(ReservationCreate reservationCreate) {
		final VehicleEntity ve = vehicleRepo.findAllByUserUsename(reservationCreate.getUsername()).stream().findFirst().orElse(null);
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
		re.setVehicle(ve);
		re.setParkingSpot(parkingSpots.stream().filter(x -> x.getAvailable()).findFirst().orElseThrow(BusinessException :: new));
		re.setStartTime(Instant.parse(reservationCreate.getStartTime()));
		re.setEndTime(Instant.parse(reservationCreate.getEndTime()));	
		re.setCreatedAt(Instant.now());
		re.setUpdatedAt(Instant.now());
		final ReservationEntity savedEntity = reservationRepo.save(re);
		return ReservationMapper.toReservation(savedEntity);
	}
	
	
}
