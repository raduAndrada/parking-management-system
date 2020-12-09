package ro.upet.parking.system.management.business.api.reservation;

import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;

public interface ReservationValidator {

	/**
	 * @param entity the reservation to be checked 
	 * @return true if the reservation can be saved, false otherwise
	 */
	public boolean validate (final ReservationEntity entity) ;
}
