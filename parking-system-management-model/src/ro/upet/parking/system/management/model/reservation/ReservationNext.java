package ro.upet.parking.system.management.model.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.upet.parking.system.management.model.base.ReservationStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
