package ro.upet.parking.system.management.model;

import java.time.Instant;

import javax.annotation.Nullable;

import org.immutables.value.Value;



import ro.upet.parking.system.management.model.ReservationStatus;
import ro.upet.parking.system.management.model.Vehicle;

/**
 * @author Andrada
 * Model for a reservation
 */
@Value.Immutable
public interface Reservation extends BaseModel{

	/**
	 * @return start time 
	 */
	Instant getStartTime();
	

	/**
	 * @return end time 
	 */
	Instant getEndTime();

	/**
	 * @return additional info for the reservation
	 */
	@Nullable
	String getNotes();

	/**
	 * @return status for the reservation 
	 */
	ReservationStatus getReservationStatus();
	

	/**
	 * @return vehicle code
	 */
	Vehicle getVehicle();

	/**
	 * @return parking spot id
	 */
	ParkingSpot getParkingSpot();

	/**
	 * @return the price of the reservation
	 */
	String getCost();

}
