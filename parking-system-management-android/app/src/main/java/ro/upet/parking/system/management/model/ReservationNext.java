package ro.upet.parking.system.management.model;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.upet.parking.system.management.model.ReservationStatus;

@Value.Immutable
@JsonSerialize(as = ImtReservationNext.class)
@JsonDeserialize(builder = ImtReservationNext.Builder.class)
public interface ReservationNext {

	/**
	 * @return days till the next reservation
	 */
	Integer getDays();


	/**
	 * @return hours till the next reservation
	 */
	Integer getHours();

	/**
	 * @return minutes till the next reservation
	 */
	Integer getMinutes();
	
	/**
	 * @return the id of the reservation
	 */
	Long getReservationId();
	
	
	/**
	 * @return get the status of the reservation
	 */
	ReservationStatus getReservationStatus();
}
