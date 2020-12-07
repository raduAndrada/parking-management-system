package ro.upet.parking.system.management.model;

import org.immutables.value.Value;


/**
 * @author Andrada
 * Model for a reservation
 */
@Value.Immutable
public interface ReservationCreate {
	
	Reservation getReservation();
	
	

}
