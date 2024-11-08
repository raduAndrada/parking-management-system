package ro.upet.parking.system.management.business.api.vehicle;

import java.util.List;

import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.model.vehicle.Vehicle;

/**
 * 
 * @author Andrada
 * Vehicle actions
 */
public interface VehicleService extends BaseService<Vehicle> {

	/**
	 * @param username the username of the user
	 * @return the list of all the vehicle for the given username
	 */
	List<Vehicle> findByUserUsername(final String username);
}
	
