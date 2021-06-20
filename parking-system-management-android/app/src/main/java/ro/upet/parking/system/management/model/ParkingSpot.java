package ro.upet.parking.system.management.model;

import androidx.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * @author Andrada
 * Parking spot model
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ParkingSpot extends BaseModel {

	/**
	 * @return number of the parking spot
	 */
	String number;
	
	/**
	 * @return true if the spot is free, false otherwise
	 */
	Boolean available;
	
	/**
	 * @return true if it can be rent through a membership, false otherwise
	 */
	Boolean rentable;
	
	/**
	 * @return true if it is rented through a membership, false otherwise
	 */
	Boolean rented;
	/**
	 * @return number of the level
	 */
	@Nullable
	String parkingLevelNumber;

	/**
	 * @return letter of the zone
	 */
	@Nullable
	String parkingZoneLetter;

	/**
	 * @return the name of the parking
	 */
	@Nullable
	String parkingName;
	
}
