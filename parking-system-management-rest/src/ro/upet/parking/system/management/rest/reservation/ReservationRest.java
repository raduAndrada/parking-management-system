package ro.upet.parking.system.management.rest.reservation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.business.api.reservation.ReservationService;
import ro.upet.parking.system.management.model.reservation.Reservation;
import ro.upet.parking.system.management.model.reservation.ReservationCreate;
import ro.upet.parking.system.management.model.reservation.ReservationNext;
import ro.upet.parking.system.management.rest.base.BaseRest;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Andrada
 * Rest controller for the reservations
 */
@RestController
@RequestMapping(value = "/v1/reservations")
@CrossOrigin(maxAge = 3600)
@Slf4j
public class ReservationRest extends BaseRest<Reservation> {

    static final String RESERVATION_CLAIM_PATH = "claim/{reservationId}";
    static final String RESERVATION_START_PATH = "start/{reservationId}";
    static final String RESERVATION_COMPLETE_PATH = "complete/{reservationId}";


    @Inject
    private ReservationService service;


    @Override
    @Inject
    public void setService(BaseService<Reservation> service) {
        super.setService(this.service);
    }

    /**
     * @param username of the user
     * @return the list with all the reservations of a user
     */
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Reservation>> getReservationsForUser(@PathVariable final String username) {
        log.info("REST request to GET reservations by username : {}", username);
        final List<Reservation> reservationList = service.findAllForUserByUsername(username);
        if (reservationList == null) {
            log.info("No reservations found for the user with username: {}", username);
            return ResponseEntity.notFound().build();
        } else {
            log.info("Reservationss found for the user: {}", reservationList.toString());
            return ResponseEntity.ok(reservationList);
        }
    }


    /**
     * @param reservationCreate the entity to be added
     * @return the created entity
     */
    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody  final ReservationCreate reservationCreate) {
        log.info("REST request to CREATE RESERVATION : {}", reservationCreate);
        final Reservation created;
        try {
            created = service.createReservation(reservationCreate);
        } catch (final Exception e) {
            log.info("Something went wrong creating the entity : {}", reservationCreate, e);
            return null;
        }
        return ResponseEntity.ok(created);
    }


    /**
     * @param username of the user
     * @return the list with all the reservations of a user
     */
    @GetMapping("/reservation-next/{username}")
    public ResponseEntity<ReservationNext> getReservationNextForUser(@PathVariable final String username) {
        log.info("RESRT request to GET reservationsNEXT by username : {}", username);
        final ReservationNext reservationNext = service.getReservationNext(username);
        if (reservationNext == null) {
            log.info("No reservations found for the user with username: {}", username);
            return ResponseEntity.notFound().build();
        } else {
            log.info("Reservations found for the user: {}", reservationNext);
            return ResponseEntity.ok(reservationNext);
        }
    }

    /**
     * @param reservationId id of the resevation to be claimed
     * @return the created entity
     */
    @PutMapping(RESERVATION_CLAIM_PATH)
    @Transactional
    public ResponseEntity<Reservation> claim(@PathVariable final Long reservationId) {
        log.info("REST request to CLAIM RESERVATION : {}", reservationId);
        final Reservation updated;
        try {
            updated = service.claim(reservationId);
        } catch (final Exception e) {
            log.info("Something went wrong UPDATING the entity : {}", reservationId);
            return null;
        }
        return ResponseEntity.ok(updated);
    }

    /**
     * @param reservationId the id of the reservation to be started
     * @return the created entity
     */
    @PutMapping(RESERVATION_START_PATH)
    @Transactional
    public ResponseEntity<Reservation> start(@PathVariable final Long reservationId) {
        log.info("REST request to CLAIM RESERVATION : {}", reservationId);
        final Reservation updated;
        try {
            updated = service.start(reservationId);
        } catch (final Exception e) {
            log.info("Something went wrong UPDATING the entity : {}", reservationId);
            return null;
        }
        return ResponseEntity.ok(updated);
    }


    /**
     * @param reservationId identifier of the completed reservation
     * @return the created entity
     */
    @PutMapping(RESERVATION_COMPLETE_PATH)
    @Transactional
    public ResponseEntity<Reservation> complete(@PathVariable final Long reservationId) {
        log.info("REST request to CLAIM RESERVATION : {}", reservationId);
        final Reservation updated;
        try {
            updated = service.complete(reservationId);
        } catch (final Exception e) {
            log.info("Something went wrong UPDATING the entity : {}", reservationId);
            return null;
        }
        return ResponseEntity.ok(updated);
    }

}
