package ro.upet.parking.system.management.rest.reservation;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.upet.parking.system.management.business.api.reservation.ReservationService;
import ro.upet.parking.system.management.model.reservation.Reservation;

/**
 * @author Andrada
 * Rest controller for the reservations
 */
@RestController
@RequestMapping(value = "/v1/reservations")
@CrossOrigin(maxAge = 3600)
public class ReservationRest {

	private static final Logger LOGGER  = Logger.getLogger(ReservationRest.class.getName());
	
	@Inject
	private ReservationService reservationService;
	
	/**
	 * @param code unique for each reservation
	 * @return the reservation with the corresponding code
	 */
	@GetMapping(path = "/code/{code}")
	public ResponseEntity<Reservation> getReservation(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to GET reservation by code: %s", code));
		final Reservation reservation = reservationService.getReservationByCode(code);
		if (reservation == null) {
			LOGGER.info(String.format("Reservation with code: %s does not exist", code));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(reservation);
		}
	}
	
	/**
	 * @param id of the reservation
	 * @return the reservation with the corresponding id
	 */
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<Reservation> getReservation(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to GET reservation by id: %s", id));
		final Reservation reservation = reservationService.getReservationById(id);
		if (reservation == null) {
			LOGGER.info(String.format("Reservation with id: %s does not exist", id));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(reservation);
		}
	}


	/**
	 * @return the list with all the reservations
	 */
	@GetMapping
	public ResponseEntity<List<Reservation>> getReservations() {
		LOGGER.info(String.format("REST request to GET all reservations"));
		final List<Reservation> reservationList= reservationService.getReservationList();
		if (reservationList == null) {
			LOGGER.info(String.format("No reservations found"));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(reservationList);
		}
	}

	/**
	 * 
	 * @param reservation the reservation to be added
	 * @return the created entity
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<Reservation> postReservation(@RequestBody final Reservation reservation) {
		LOGGER.info(String.format("REST request to POST reservation : %s", reservation));
		final Reservation createdReservation;
		try {
			createdReservation = reservationService.addReservation(reservation);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong creating the reservation : %s", reservation));
			return null;
		}
		return ResponseEntity.ok(createdReservation);
	}
	
	
	/**
	 * 
	 * @param reservation the reservation to be added
	 * @return the created entity
	 */
	@PutMapping
	@Transactional
	public ResponseEntity<Reservation> putReservation(@RequestBody final Reservation reservation) {
		LOGGER.info(String.format("REST request to PUT reservation : %s", reservation));
		final Reservation updatedReservation;
		try {
			updatedReservation = reservationService.updateReservation(reservation);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong updating the reservation: %s", reservation));
			return null;
		}
		return ResponseEntity.ok(updatedReservation);
	}

	
	/**
	 * 
	 * @param id the identifier for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/id/{id}")
	@Transactional
	public ResponseEntity<Reservation> deleteReservation(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to DELETE reservation with id : %s", id));
		final Reservation deletedReservation;
		try {
			deletedReservation = reservationService.removeReservationById(id);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the reservation with the id: %s", id));
			return null;
		}
		return ResponseEntity.ok(deletedReservation);
	}
	
	/**
	 * 
	 * @param code the unique code for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/code/{code}")
	@Transactional
	public ResponseEntity<Reservation> deleteReservation(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to DELETE reservation with code : %s", code));
		final Reservation deletedReservation;
		try {
			deletedReservation = reservationService.removeReservationByCode(code);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the reservation with the code: %s", code));
			return null;
		}
		return ResponseEntity.ok(deletedReservation);
	}

}
