package ro.upet.parking.system.management.model;

import java.time.Instant;

import org.immutables.value.Value;

/**
 * @author Andrada
 * Model for representing a parking
 */
@Value.Immutable
public interface Parking {
	
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