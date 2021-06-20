package ro.upet.parking.system.management.model;

import java.util.List;

import javax.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * @author Andrada
 * Model that represents a level in a parking
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ParkingLevel extends BaseModel{

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
	@Nullable
	List<ParkingZone> parkingZones;
}
