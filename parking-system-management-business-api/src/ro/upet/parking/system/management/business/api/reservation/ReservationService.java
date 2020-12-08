package ro.upet.parking.system.management.business.api.reservation;

import java.util.List;

import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.model.reservation.Reservation;
import ro.upet.parking.system.management.model.reservation.ReservationCreate;

public interface ReservationService extends BaseService<Reservation> {
	
	/**
	 * @param username the username of the user
	 * @return the list of all the reservations he had
	 * 
	 */
	public List<Reservation> findAllForUserByUsername(final String username);
	
	/**
	 * @param reservation the details for the reservation
	 * @return the created reservation
	 */
	public Reservation reserveSpot(final Reservation reservation);
	
	/**
	 * @param reservationCreate the details for the reservation to be created
	 * @return the created reservation
	 */
	public Reservation createReservation(final ReservationCreate reservationCreate);
}
