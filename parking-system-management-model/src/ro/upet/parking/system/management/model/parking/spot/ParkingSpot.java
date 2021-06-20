package ro.upet.parking.system.management.model.parking.spot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.upet.parking.system.management.model.base.BaseModel;

/**
 * @author Andrada
 * Parking spot model
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParkingSpot  extends BaseModel{
	
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
	String parkingLevelNumber;
	
	/**
	 * @return letter of the zone
	 */
	String parkingZoneLetter;

	/**
	 * @return the name of the parking
	 */
	String parkingName;

	@Override
	public String toString() {
		return "ParkingSpot{" +
				"number='" + number + '\'' +
				", available=" + available +
				", rentable=" + rentable +
				", rented=" + rented +
				", parkingName='" + parkingName + '\'' +
				'}';
	}
	
}
