package ro.upet.parking.system.management.model.reservation;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Andrada
 * Model for a reservation
 */
@Value.Immutable
@JsonSerialize(as = ImtReservationCreate.class)
@JsonDeserialize(builder = ImtReservationCreate.Builder.class)
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
