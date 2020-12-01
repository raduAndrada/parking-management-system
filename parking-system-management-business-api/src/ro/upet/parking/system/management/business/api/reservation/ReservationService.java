package ro.upet.parking.system.management.business.api.reservation;

import java.util.List;

import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.model.reservation.Reservation;

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
	 * @throws BusinessException if the reservation does not exist
	 */
	public Reservation removeReservationById(final Long ReservationId) throws BusinessException;
	
	/**
	 * @param ReservationCode the code of the entity that will be deleted
	 * @return the deleted entity
	 */
	public Reservation removeReservationByCode(final String ReservationCode);
	
	/**
	 * @param reservation the details for the reservation
	 * @return the created reservation
	 */
	public Reservation reserveSpot(final Reservation reservation);
}
