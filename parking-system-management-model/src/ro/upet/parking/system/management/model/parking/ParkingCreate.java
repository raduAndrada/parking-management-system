package ro.upet.parking.system.management.model.parking;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImtParkingCreate.class)
@JsonDeserialize(builder = ImtParkingCreate.Builder.class)
public interface ParkingCreate {
	
	Parking getParking();
	
	Integer getNumberOfLevels();
	
	char getParkingZoneStartingLetter();
	
	char getParkingZoneEndingLetter();
	
	Integer getParkingZoneSpotNumber();

}
