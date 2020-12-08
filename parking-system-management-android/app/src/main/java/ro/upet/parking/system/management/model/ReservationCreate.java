package ro.upet.parking.system.management.model;

import org.immutables.value.Value;


/**
 * @author Andrada
 * Model for a reservation
 */
@Value.Immutable
@Value.Modifiable
public interface ReservationCreate {

	/**
	 * @return the username for the reservation
	 */
	String getUsername();


	/**
	 * @return the name of the parking
	 */
	String getParkingName();


	/**
	 * @return the start time for the reservation
	 */
	String getStartTime();



	/**
	 * @return the end time for the reservation
	 */
	String getEndTime();

}
