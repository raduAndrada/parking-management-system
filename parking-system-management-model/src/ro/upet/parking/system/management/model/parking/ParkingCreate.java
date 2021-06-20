package ro.upet.parking.system.management.model.parking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingCreate {

	Parking parking;
	
	Integer numberOfLevels;
	
	char parkingZoneStartingLetter;
	
	char parkingZoneEndingLetter;
	
	Integer parkingZoneSpotNumber;

}
