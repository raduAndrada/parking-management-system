package ro.upet.parking.system.management.rest.reservation;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.business.api.reservation.ReservationService;
import ro.upet.parking.system.management.model.reservation.Reservation;
import ro.upet.parking.system.management.model.reservation.ReservationCreate;
import ro.upet.parking.system.management.rest.base.BaseRest;

/**
 * @author Andrada
 * Rest controller for the reservations
 */
@RestController
@RequestMapping(value = "/v1/reservations")
@CrossOrigin(maxAge = 3600)
public class ReservationRest extends BaseRest<Reservation>{

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
		LOGGER.info(String.format("RESRT request to GET reservations by username : %s", username));
		final List<Reservation> reservationList= service.findAllForUserByUsername(username);
		if (reservationList == null) {
			LOGGER.info(String.format("No memberships found for the user with id: %s", username));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(reservationList);
		}
	}
	

	/**
	 * 
	 * @param entity the entity to be added
	 * @return the created entity
	 */
	@PostMapping("/create")
	@Transactional
	public ResponseEntity<Reservation> createReservation(@RequestBody final ReservationCreate reservationCreate) {
		LOGGER.info(String.format("REST request to CREATE RESERVATION : %s", reservationCreate));
		final Reservation created;
		try {
			created = service.createReservation(reservationCreate);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong creating the entity : %s", reservationCreate));
			return null;
		}
		return ResponseEntity.ok(created);
	}
	
	

}
