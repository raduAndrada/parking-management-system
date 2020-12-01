package ro.upet.parking.system.management.model.parking.spot;

import javax.annotation.Nullable;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Andrada
 * Parking spot model search criteria
 */
@Value.Immutable
@JsonSerialize(as = ImtParkingSpotSearchCriteria.class)
@JsonDeserialize(builder = ImtParkingSpotSearchCriteria.Builder.class)
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
