package ro.upet.parking.system.management.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ReservationNext {

	/**
	 * @return days till the next reservation
	 */
	Integer days;


	/**
	 * @return hours till the next reservation
	 */
	Integer hours;

	/**
	 * @return minutes till the next reservation
	 */
	Integer minutes;
	
	/**
	 * @return the id of the reservation
	 */
	Long reservationId;

	/**
	 * @return duration in hours
	 */
	Integer durationHours;


	/**
	 * @return duration in minutes
	 */
	Integer durationMinutes;
	
	
	/**
	 * @return  the status of the reservation
	 */
	ReservationStatus reservationStatus;
}
