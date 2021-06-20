package ro.upet.parking.system.management.model;

import java.util.List;

import javax.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Andrada
 * Parking zone model
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ParkingZone extends BaseModel {
	
	/**
	 * @return the letter for the zone
	 */
	String letter;


	/**
	 * @return list of all the parking spots
	 */
	@Nullable
	List<ParkingSpot> parkingSpots;
	
}
