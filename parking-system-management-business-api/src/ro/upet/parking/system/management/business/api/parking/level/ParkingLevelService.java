package ro.upet.parking.system.management.business.api.parking.level;

import java.util.List;

import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.model.parking.level.ParkingLevel;

public interface ParkingLevelService extends BaseService<ParkingLevel> {
	
	/**
	 * @param parkingId the parking
	 * @return the list of all the levels of the parking
	 */
	public List<ParkingLevel> getParkingLevelListByParking(final Long parkingId);
}
