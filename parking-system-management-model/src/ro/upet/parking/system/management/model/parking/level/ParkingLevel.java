package ro.upet.parking.system.management.model.parking.level;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.upet.parking.system.management.model.base.BaseModel;
import ro.upet.parking.system.management.model.parking.Parking;
import ro.upet.parking.system.management.model.parking.zone.ParkingZone;

import java.util.List;


/**
 * @author Andrada
 * Model that represents a level in a parking
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParkingLevel  extends BaseModel{

	/**
	 * @return number of the level
	 */
	String number;
	
	/**
	 * @return id for the parking
	 */
	Parking parking;
	
	
	/**
	 * @return the parking zones for this level
	 */
	List<ParkingZone> parkingZones;

	@Override
	public String toString() {
		return "ParkingLevel{" +
				"number='" + number + '\'' +
				", parking=" + parking +
				'}';
	}
}
