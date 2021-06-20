package ro.upet.parking.system.management.model.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Andrada
 * Model for a reservation
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationCreate {
	
	/**
	 * @return the username for the reservation
	 */
	String username;
	

	/**
	 * @return the name of the parking
	 */
	String parkingName;
	
	
	/**
	 * @return the start time for the reservation
	 */
	String startTime;
	
	

	/**
	 * @return the end time for the reservation
	 */
	String endTime;

}
