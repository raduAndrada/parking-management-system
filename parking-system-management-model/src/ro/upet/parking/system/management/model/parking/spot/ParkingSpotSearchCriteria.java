package ro.upet.parking.system.management.model.parking.spot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Andrada
 * Parking spot model search criteria
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotSearchCriteria {

	/**
	 * @return parking level sort options
	 */
	ParkingSpotLevelSortOptions parkingspotlevelsortoptions;
	
	/**
	 * @return zone sort options
	 */
	ParkingSpotZoneSortOptions parkingspotzonesortoptions;
	
	/**
	 * @return parking spot preference options
	 */
	ParkingSpotPreferenceOptions parkingspotpreferenceoptions;
}
