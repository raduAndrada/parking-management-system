package ro.upet.parking.system.management.business.api.vehicle;

import ro.upet.parking.system.management.model.vehicle.Vehicle;

/**
 * @author Andrada
 * Validator for the constraints of a vehicle
 */
public interface VehicleValidator {
	/**
	 * @param vehicle the entity to be validated
	 * @return true is the vehicle is defined correctly, false otherwise
	 */
	boolean validate (final Vehicle vehicle);
}
