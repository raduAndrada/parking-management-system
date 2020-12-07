package ro.upet.parking.system.management.model;

import javax.annotation.Nullable;

import org.immutables.value.Value;

/**
 * @author Andrada
 * Parking spot model search criteria
 */
@Value.Immutable
public interface ParkingSpotSearchCriteria {

	/**
	 * @return parking level sort options
	 */
	@Nullable
	ParkingSpotLevelSortOptions getParkingSpotLevelSortOptions();
	
	/**
	 * @return zone sort options
	 */
	@Nullable
	ParkingSpotZoneSortOptions getParkingSpotZoneSortOptions();
	
	/**
	 * @return parking spot preference options
	 */
	@Nullable
	ParkingSpotPreferenceOptions getParkingSpotPreferenceOptions();
}
