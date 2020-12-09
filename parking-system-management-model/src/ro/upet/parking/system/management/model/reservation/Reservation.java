package ro.upet.parking.system.management.model.reservation;

import java.time.Instant;

import javax.annotation.Nullable;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.upet.parking.system.management.model.base.BaseModel;
import ro.upet.parking.system.management.model.base.ReservationStatus;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;
import ro.upet.parking.system.management.model.user.User;

/**
 * @author Andrada
 * Model for a reservation
 */
@Value.Immutable
@JsonSerialize(as = ImtReservation.class)
@JsonDeserialize(builder = ImtReservation.Builder.class)
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
	 * @return user for the reservation
	 */
	User getUser();	
	
	/**
	 * @return parking spot id
	 */
	ParkingSpot getParkingSpot();
	
	/**
	 * @return the price of the reservation
	 */
	String getCost();
	

}
