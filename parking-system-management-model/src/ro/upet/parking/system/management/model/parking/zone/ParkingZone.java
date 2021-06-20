package ro.upet.parking.system.management.model.parking.zone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.upet.parking.system.management.model.base.BaseModel;
import ro.upet.parking.system.management.model.parking.level.ParkingLevel;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;

import java.util.List;

/**
 * @author Andrada
 * Parking zone model
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParkingZone extends BaseModel {

	/**
	 *the letter for the zone
	 */
	String letter;


	/**
	 * list of all the parking spots
	 */
	List<ParkingSpot> parkingSpots;
	
	/**
	 * parking level
	 */
	ParkingLevel parkingLevel;

	@Override
	public String toString() {
		return "ParkingZone{" +
				"letter='" + letter + '\'' +
				", parkingLevelNumber=" + parkingLevel +
				'}';
	}
}
