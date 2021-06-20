package ro.upet.parking.system.management.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * @author Andrada
 * Model for a reservation
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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
