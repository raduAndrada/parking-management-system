package ro.utcn.parking.system.management.model.reservation;

import java.time.Instant;

import javax.annotation.Nullable;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.utcn.parking.system.management.model.base.ReservationStatus;

/**
 * @author Andrada
 * Model for a reservation
 */
@Value.Immutable
@JsonSerialize(as = ImtReservation.class)
@JsonDeserialize(builder = ImtReservation.Builder.class)
public interface Reservation {

	/**
	 * @return identifier for the entity
	 */
	Long getId();
	
	/**
	 * @return unique code for the entity
	 */
	String getCode();	

	/**
	 * @return creation time
	 */
	Instant getCreatedAt();

	/**
	 * @return last update time
	 */
	Instant getUpdatedAt();
	
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
	 * @return registration number of the vehicle that will be parked 
	 */
	String getVehicleLicencePlate();
	
	/**
	 * @return the id of the vehicle
	 */
	Long getVehicleId();

	/**
	 * @return vehicle code
	 */
	String getVehicleCode();	
	
	/**
	 * @return parking spot id
	 */
	Long getParkingSpotId();
	
	/**
	 * @return parking spot code 
	 */
	String getParkingSpotCode();	
}
