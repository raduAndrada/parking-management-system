package ro.upet.parking.system.management.business.api.parking;

import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.model.parking.Parking;
import ro.upet.parking.system.management.model.parking.ParkingCreate;

/**
 * @author Andrada
 * Service for creating a parking 
 */
public interface ParkingService extends BaseService<Parking>{

	/**
	 * @param parkingCreate the configuration for the parking
	 * @return the created parking
	 */
	ParkingCreate configureParking(final ParkingCreate parkingCreate);
}
