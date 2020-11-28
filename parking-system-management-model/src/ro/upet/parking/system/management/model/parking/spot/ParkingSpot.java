package ro.upet.parking.system.management.model.parking.spot;

import java.time.Instant;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.utcn.parking.system.management.model.parking.spot.ImtParkingSpot;

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
	 * @return id for the parking zone
	 */
	Long getParkingZoneId();
	
	/**
	 * @return parking zone's code
	 */
	String getParkingZoneCode();
	
}
