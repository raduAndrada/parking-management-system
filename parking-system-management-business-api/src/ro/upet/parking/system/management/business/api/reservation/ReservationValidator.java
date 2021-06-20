package ro.upet.parking.system.management.business.api.reservation;

import java.time.Instant;

/**
 * @author Andrada
 * Validate a reservation before storing a new one
 */
public interface ReservationValidator {

	/**
	 * @param entity the reservation to be checked 
	 * @return true if the reservation can be saved, false otherwise
	 */
	boolean validate (final Long parkingSpotId, final Instant startTime, final Instant endTime) ;
}
