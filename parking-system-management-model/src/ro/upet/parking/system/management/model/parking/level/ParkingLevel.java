package ro.upet.parking.system.management.model.parking.level;

import java.util.List;

import javax.annotation.Nullable;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.upet.parking.system.management.model.base.BaseModel;
import ro.upet.parking.system.management.model.parking.Parking;
import ro.upet.parking.system.management.model.parking.zone.ParkingZone;


/**
 * @author Andrada
 * Model that represents a level in a parking
 */
@Value.Immutable
@JsonSerialize(as = ImtParkingLevel.class)
@JsonDeserialize(builder = ImtParkingLevel.Builder.class)
public interface ParkingLevel  extends BaseModel{

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
