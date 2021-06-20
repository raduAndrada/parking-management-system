package ro.upet.parking.system.management.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ParkingCreate {
	
	Parking parking;
	
	Integer numberOfLevels;
	
	char parkingZoneStartingLetter;
	
	char parkingZoneEndingLetter;
	
	Integer parkingZoneSpotNumber;

}
