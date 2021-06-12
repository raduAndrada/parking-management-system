package ro.upet.parking.system.management.business.impl.vehicle;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import ro.upet.parking.system.management.business.api.vehicle.VehicleValidator;
import ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository;
import ro.upet.parking.system.management.model.vehicle.Vehicle;


@Service
public class VehicleValidatorImpl implements VehicleValidator{

	final
	VehicleRepository vehicleRepo;

	public VehicleValidatorImpl(VehicleRepository vehicleRepo) {
		this.vehicleRepo = vehicleRepo;
	}

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
