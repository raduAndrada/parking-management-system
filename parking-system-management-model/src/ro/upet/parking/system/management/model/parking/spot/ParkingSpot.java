package ro.upet.parking.system.management.model.parking.spot;

import java.time.Instant;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Andrada
 * Parking spot model
 */
@Value.Immutable
@JsonSerialize(as = ImtParkingSpot.class)
@JsonDeserialize(builder = ImtParkingSpot.Builder.class)
public interface ParkingSpot {

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
	 * @return number of the parking spot
	 */
	String getNumber();
	
	/**
	 * @return true if the spot is free, false otherwise
	 */
	Boolean isAvailable();
	
	/**
	 * @return true if it can be rent through a membership, false otherwise
	 */
	Boolean isRentable();

	
}
