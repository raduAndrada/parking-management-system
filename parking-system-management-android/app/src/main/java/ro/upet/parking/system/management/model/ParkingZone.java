package ro.upet.parking.system.management.model;

import java.time.Instant;
import java.util.List;

import javax.annotation.Nullable;

import org.immutables.value.Value;


import ro.upet.parking.system.management.model.ParkingSpot;

/**
 * @author Andrada
 * Parking zone model
 */
@Value.Immutable
public interface ParkingZone extends BaseModel {
	
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
