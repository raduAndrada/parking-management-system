package ro.upet.parking.system.management.business.impl.reservation;

import lombok.extern.slf4j.Slf4j;
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
import ro.upet.parking.system.management.model.reservation.Reservation;
import ro.upet.parking.system.management.model.reservation.ReservationCreate;
import ro.upet.parking.system.management.model.reservation.ReservationNext;

import javax.transaction.Transactional;
import java.time.*;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Andrada
 * Business level logic implementation for reservations
 */
@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    @Value("${reservation.claim.allocated.time}")
    private Integer RESERVATION_EXPIRATION_TIME; // 15 minutes

    private final ReservationValidator reservationValidator;

    private final ReservationRepository reservationRepo;

    private final UserRepository userRepo;

    private final ParkingSpotRepository parkingSpotRepo;

    public ReservationServiceImpl(ReservationValidator reservationValidator, ReservationRepository reservationRepo, UserRepository userRepo, ParkingSpotRepository parkingSpotRepo) {
        this.reservationValidator = reservationValidator;
        this.reservationRepo = reservationRepo;
        this.userRepo = userRepo;
        this.parkingSpotRepo = parkingSpotRepo;
    }

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
        if (entity == null) {
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
        return ReservationMapper.toReservationList(reservationRepo.findAllByUserUsername(username));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Reservation createReservation(final ReservationCreate reservationCreate) {
        // find the user for the reservation if none throw exception
        final UserEntity ue = userRepo.findByUsername(reservationCreate.getUsername())
                .orElseThrow(BusinessException::new);

        // find all available parking spots by parking name
        final List<ParkingSpotEntity> parkingSpots = parkingSpotRepo.findAllAvailableByParkingName(reservationCreate.getParkingName());
        final ReservationEntity re = ReservationEntity.builder()
                .user(ue)
                .startTime(Instant.parse(reservationCreate.getStartTime()))
                .endTime(Instant.parse(reservationCreate.getEndTime()))
                .reservationStatus(ReservationStatus.PENDING)
                .build();


        // validate parking spot availability during the selected time
        re.setParkingSpot(parkingSpots.stream()
                .filter(x -> reservationValidator.validate(x.getId(), re.getStartTime(), re.getEndTime()))
                .findFirst().orElseThrow(BusinessException::new));

        //save reservation
        // set cost as price per hour * reservation duration
        re.setCost(computeCost(re.getEndTime(), re.getStartTime(), re.getParkingSpot().getParkingZone().getParkingLevel().getParking().getPricePerHour()));
        final ReservationEntity savedEntity = reservationRepo.save(re);
        startCountdownTimer(savedEntity);
        return ReservationMapper.toReservation(savedEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReservationNext getReservationNext(final String username) {

        // all upcoming reservations have the pending status
        final List<ReservationEntity> reservations = reservationRepo.findAllByUserUsernameAndReservationStatus(username, ReservationStatus.PENDING);

        // find the closest upcoming reservation
        final Optional<ReservationEntity> re = reservations.stream().sorted((r1, r2) -> r1.getStartTime().isBefore(r2.getStartTime()) ? 1 : -1)
                .findFirst();
        ReservationNext reservationNext = null;
        if (re.isPresent()) {
            reservationNext = getReservationNext(re.get());
        }

        return reservationNext;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Reservation claim(final Long reservationId) {
        return updateReservation(reservationId, ReservationStatus.CLAIMED, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Reservation start(final Long reservationId) {
        return updateReservation(reservationId, ReservationStatus.ONGOING, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Reservation complete(final Long reservationId) {
        return updateReservation(reservationId, ReservationStatus.COMPLETED, true);
    }


    /**
     * @param reservationEntity the reservation
     */
    private void startCountdownTimer(final ReservationEntity reservationEntity) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    ReservationEntity re = reservationRepo.findById(reservationEntity.getId())
                            .orElseThrow(BusinessException::new);
                    re.setReservationStatus(ReservationStatus.UNCLAIMED);
                    re.setParkingSpot(reservationEntity.getParkingSpot());
                    reservationRepo.save(re);
                    claimReservation(re);
                } catch (Exception e) {
                    log.info("Failed to make reservation in unclaimed {}", e);
                }
            }
        };

        // schedule the status to change to unclaimed when the reservation is starting
        // note Instant.now() gives the current time - 3h
        long duration = Math.abs(Duration.between(reservationEntity.getStartTime(),
                Instant.now()).toMinutes()) - 180;
        log.info("Reservation status changing to UNCLAIMED in : {} minutes", duration);
        new Timer().schedule(timerTask, duration * 60000);
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
                    log.error("Failed to remove  unclaimed reservation: {}", reservationEntity.getId(), e);
                }
            }
        };

        // schedule a timer to check if the status is changed to claimed if not just remove the reservation
        long duration = Math.abs(Duration.between(reservationEntity.getStartTime().plusSeconds(RESERVATION_EXPIRATION_TIME),
                reservationEntity.getStartTime()).toMinutes());
        log.info("Reservation will be deleted in : {} minutes", duration);
        new Timer().schedule(timerTask, duration * 60000);
    }

    private Reservation updateReservation(final Long reservationId, final ReservationStatus status, final boolean available) {
        ReservationEntity re = reservationRepo.findById(reservationId).orElseThrow(BusinessException::new);
        re.setReservationStatus(status);
        reservationRepo.save(re);

        //make spot occupied
        ParkingSpotEntity pse = parkingSpotRepo.findById(re.getParkingSpot().getId())
                .orElseThrow(BusinessException::new);
        pse.setAvailable(available);
        parkingSpotRepo.save(pse);
        return ReservationMapper.toReservation(re);
    }

    private static Double computeCost(final Instant startTime, final Instant endTime, final double pricePerHour) {
        final long duration = Duration.between(endTime, startTime).toMinutes();
        return pricePerHour * duration / 60;
    }

    private static ReservationNext getReservationNext(final ReservationEntity entity) {
        // compute times for count down timers
        LocalDateTime startTimeLDT = LocalDateTime.ofInstant(entity.getStartTime(), ZoneOffset.UTC);
        LocalDateTime currentTimeLDT = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
        LocalDateTime endTimeLDT = LocalDateTime.ofInstant(entity.getEndTime(), ZoneOffset.UTC);


        final Integer daysTillReservation = startTimeLDT.getDayOfYear() - currentTimeLDT.getDayOfYear() - currentTimeLDT.getHour() > startTimeLDT.getHour() ? 1 : 0;
        final Integer hoursTillReservation = startTimeLDT.getHour() - currentTimeLDT.getHour() - 3;
        final Integer minutesTillReservation = startTimeLDT.getMinute() - currentTimeLDT.getMinute();


        final Integer reservationDurationHours = Math.abs(endTimeLDT.getHour() - startTimeLDT.getHour());
        final Integer reservationDurationMinutes = Math.abs(endTimeLDT.getMinute() - startTimeLDT.getMinute());


        return ReservationNext.builder()
                .days(daysTillReservation)
                .hours(correctNegativeTimes(hoursTillReservation, 24))
                .minutes(correctNegativeTimes(minutesTillReservation, 60))
                .reservationId(entity.getId())
                .durationHours(reservationDurationHours)
                .durationMinutes(reservationDurationMinutes)
                .reservationStatus(entity.getReservationStatus())
                .build();
    }

    private static int correctNegativeTimes(final int time, final int correction) {
        if (time < 0) {
            return time + correction;
        } else {
            return time;
        }
    }


}
