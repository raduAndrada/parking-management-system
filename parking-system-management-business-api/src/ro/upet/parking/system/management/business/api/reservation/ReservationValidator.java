package ro.upet.parking.system.management.business.api.reservation;

import java.time.Instant;

/**
 * @author Andrada
 * Validate a reservation before storing a new one
 */
public interface ReservationValidator {

	/**
	 * @param parkingSpotId spot checked
	 * @param endTime end time of the reservation
	 * @param startTime  start time of the reservation
	 * @return true if the reservation can be saved, false otherwise
	 */
	boolean isValidReservation(final Long parkingSpotId, final Instant startTime, final Instant endTime) ;
}
