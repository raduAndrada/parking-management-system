package ro.upet.parking.system.management.model.parking.zone;

import java.time.Instant;
import java.util.List;

import javax.annotation.Nullable;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;

/**
 * @author Andrada
 * Parking zone model
 */
@Value.Immutable
@JsonSerialize(as = ImtParkingZone.class)
@JsonDeserialize(builder = ImtParkingZone.Builder.class)
public interface ParkingZone {

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
	 * @return the letter for the zone
	 */
	String getLetter();


	/**
	 * @return list of all the parking spots
	 */
	@Nullable
	List<ParkingSpot> getParkingSpots();
	
}
