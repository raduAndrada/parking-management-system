package ro.upet.parking.system.management.business.impl.vehicle;

import javax.inject.Inject;

import ro.upet.parking.system.management.business.api.vehicle.VehicleValidator;
import ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository;
import ro.upet.parking.system.management.model.vehicle.Vehicle;


public class VehicleValidatorImpl implements VehicleValidator{
	
	
	@Inject
	VehicleRepository vehicleRepo;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validate(Vehicle vehicle) {
		if (vehicleRepo.findOptionalByLicencePlate(vehicle.getLicencePlate()).isPresent()) {
			return false;
		}
		// TODO figure out if we want to permit foreign cars
		return true;
	}

}
