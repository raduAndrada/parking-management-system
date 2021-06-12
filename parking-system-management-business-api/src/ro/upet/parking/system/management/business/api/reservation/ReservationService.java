package ro.upet.parking.system.management.business.api.reservation;

import java.util.List;

import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.model.reservation.Reservation;
import ro.upet.parking.system.management.model.reservation.ReservationCreate;
import ro.upet.parking.system.management.model.reservation.ReservationNext;

/**
 * @author Andrada
 * Service to handle reservation entities
 */
public interface ReservationService extends BaseService<Reservation> {
	
	/**
	 * @param username the username of the user
	 * @return the list of all the reservations he had
	 * 
	 */
	List<Reservation> findAllForUserByUsername(final String username);


	/**
	 * @param reservationCreate the details for the reservation to be created
	 * @return the created reservation
	 */
	Reservation createReservation(final ReservationCreate reservationCreate);

	/**
	 * @param username the user for which we search for the next reservation
	 * @return the next reservation of the user with the requested username
	 */
	ReservationNext getReservationNext(final String username);

	/**
	 * @param reservationId the reservation that needs to be claimed
	 * @return the updated value
	 */
	Reservation claim(final Long reservationId);


	/**
	 * @param reservationId the id of the reservation
	 * @return the completed reservation
	 */
	Reservation complete(final Long reservationId);


	/**
	 * @param reservationId the id of the reservation
	 * @return the started reservation
	 */
	Reservation start(Long reservationId);
}
