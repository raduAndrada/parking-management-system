package ro.upet.parking.system.management.business.api.vehicle;

/**
 * @author Andrada
 * Validator for the constraints of a vehicle
 */
public interface VehicleValidator {
	/**
	 * @param licencePlate to be validated
	 * @return true is the vehicle is defined correctly, false otherwise
	 */
	void validate (final String licencePlate);
}
