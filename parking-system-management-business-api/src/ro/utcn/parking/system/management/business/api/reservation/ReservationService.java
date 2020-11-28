package ro.utcn.parking.system.management.business.api.reservation;

import java.util.List;

import ro.utcn.parking.system.management.model.reservation.Reservation;

public interface ReservationService {
	/**
	 * @param ReservationId the id of the Reservation searched
	 * @return the requested Reservation
	 */
	public Reservation getReservationById(final Long ReservationId);
	
	/**
	 * @param ReservationCode the code of the Reservation searched 
	 * @return the requested Reservation
	 */
	public Reservation getReservationByCode(final String ReservationCode);
	
	/**
	 * @return the list of all the Reservations
	 */
	public List<Reservation> getReservationList();
	
	/**
	 * @param Reservation the entity to be added
	 * @return the added entity
	 */
	public Reservation addReservation(final Reservation Reservation);
	
	/**
	 * @param Reservation the updated Reservation
	 * @return the updated entity
	 */
	public Reservation updateReservation(final Reservation Reservation);
	
	/**
	 * @param ReservationId the id of the entity that will be deleted
	 * @return the deleted entity
	 * @throws Exception 
	 */
	public Reservation removeReservationById(final Long ReservationId) throws Exception;
	
	/**
	 * @param ReservationCode the code of the entity that will be deleted
	 * @return the deleted entity
	 */
	public Reservation removeReservationByCode(final String ReservationCode);
}
