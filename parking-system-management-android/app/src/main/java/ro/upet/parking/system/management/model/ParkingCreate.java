package ro.upet.parking.system.management.model;

import org.immutables.value.Value;


@Value.Immutable
public interface ParkingCreate {
	
	Parking getParking();
	
	Integer getNumberOfLevels();
	
	char getParkingZoneStartingLetter();
	
	char getParkingZoneEndingLetter();
	
	Integer getParkingZoneSpotNumber();

}
