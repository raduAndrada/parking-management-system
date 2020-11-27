package ro.utcn.parking.system.management.model.parking.zone;

import java.time.Instant;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
	 * @return the id for the level
	 */
	Long getParkingLevelId();
	
	/**
	 * @return code of the level
	 */
	String getParkingLevelCode();
	
}
