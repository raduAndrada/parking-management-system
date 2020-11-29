package ro.upet.parking.system.management.model.parking;

import java.time.Instant;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * @author Andrada
 * Model for representing a parking
 */
@Value.Immutable
@JsonSerialize(as = ImtParking.class)
@JsonDeserialize(builder = ImtParking.Builder.class)
public interface Parking {

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
	 * @return the name of the parking
	 */
	String getName();
	
	/**
	 * @return the location of the parking
	 */
	String getLocation();
	
	/**
	 * @return open time
	 */
	String getOpensAt();
	
	/**
	 * @return closing time
	 */
	String getClosesAt();

	
}