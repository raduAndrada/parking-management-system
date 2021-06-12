package ro.upet.parking.system.management.model.parking.zone;

import java.util.List;

import javax.annotation.Nullable;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.upet.parking.system.management.model.base.BaseModel;
import ro.upet.parking.system.management.model.parking.level.ParkingLevel;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;

/**
 * @author Andrada
 * Parking zone model
 */
@Value.Immutable
@Value.Modifiable
@JsonSerialize(as = ImtParkingZone.class)
@JsonDeserialize(builder = ImtParkingZone.Builder.class)
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
	
	/**
	 * @return parking level
	 */
	@Nullable
	ParkingLevel getParkingLevel();
	
}
