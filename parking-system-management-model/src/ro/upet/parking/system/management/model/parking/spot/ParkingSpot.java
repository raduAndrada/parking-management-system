package ro.upet.parking.system.management.model.parking.spot;

import javax.annotation.Nullable;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.upet.parking.system.management.model.base.BaseModel;

/**
 * @author Andrada
 * Parking spot model
 */
@Value.Immutable
@JsonSerialize(as = ImtParkingSpot.class)
@JsonDeserialize(builder = ImtParkingSpot.Builder.class)
public interface ParkingSpot extends BaseModel{

	
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
	
	/**
	 * @return true if it is rented through a membership, false otherwise
	 */
	Boolean isRented();
	
	/**
	 * @return number of the level
	 */
	@Nullable
	String getParkingLevelNumber();
	
	/**
	 * @return letter of the zone
	 */
	@Nullable
	String getParkingZoneLetter();
	
	/**
	 * @return the name of the parking
	 */
	@Nullable
	String getParkingName();

	
}
