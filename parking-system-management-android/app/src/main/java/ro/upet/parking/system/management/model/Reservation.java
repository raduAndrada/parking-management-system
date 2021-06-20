package ro.upet.parking.system.management.model;

import javax.annotation.Nullable;

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
public class Reservation extends BaseModel{

	/**
	 * @return start time 
	 */
	String startTime;
	

	/**
	 * @return end time 
	 */
	String endTime;

	/**
	 * @return additional info for the reservation
	 */
	@Nullable
	String notes;

	/**
	 * @return status for the reservation 
	 */
	ReservationStatus reservationStatus;

	/**
	 * @return user for the reservation
	 */
	User user;

	/**
	 * @return parking spot id
	 */
	ParkingSpot parkingSpot;

	/**
	 * @return the price of the reservation
	 */
	String cost;

}
