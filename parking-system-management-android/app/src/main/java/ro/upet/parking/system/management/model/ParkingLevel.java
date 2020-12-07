package ro.upet.parking.system.management.model;

import java.time.Instant;
import java.util.List;

import javax.annotation.Nullable;

import org.immutables.value.Value;



import ro.upet.parking.system.management.model.Parking;
import ro.upet.parking.system.management.model.ParkingZone;


/**
 * @author Andrada
 * Model that represents a level in a parking
 */
@Value.Immutable
public interface ParkingLevel extends BaseModel{

	/**
	 * @return number of the level
	 */
	String getNumber();
	
	/**
	 * @return id for the parking
	 */
	Parking getParking();
	
	
	/**
	 * @return the parking zones for this level
	 */
	@Nullable
	List<ParkingZone> getParkingZones();
}
