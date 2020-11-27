package ro.utcn.parking.system.management.model.parking.level;

import java.time.Instant;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * 
 * @author Andrada
 * Model that represents a level in a parking
 */
@Value.Immutable
@JsonSerialize(as = ImtParkingLevel.class)
@JsonDeserialize(builder = ImtParkingLevel.Builder.class)
public interface ParkingLevel {

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
	 * @return number of the level
	 */
	String getNumber();
	
	/**
	 * @return id for the parking
	 */
	Long getParkingId();
	
	/**
	 * @return code for the parking
	 */
	String getParkingCode();
}
