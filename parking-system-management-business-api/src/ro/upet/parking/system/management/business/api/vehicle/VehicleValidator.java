package ro.upet.parking.system.management.business.api.vehicle;

/**
 * @author Andrada
 * Validator for the constraints of a vehicle
 */
public interface VehicleValidator {
	/**
	 * @param licencePlate to be validated
	 * @throws ro.upet.parking.system.management.business.api.core.BusinessException if the licence plate is taken
	 */
	void validate (final String licencePlate);
}
