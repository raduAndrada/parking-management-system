package ro.upet.parking.system.management.business.impl.vehicle;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.upet.parking.system.management.business.api.vehicle.VehicleValidator;
import ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository;
import ro.upet.parking.system.management.model.vehicle.Vehicle;


@Service
@AllArgsConstructor
public class VehicleValidatorImpl implements VehicleValidator{

	private final static String LICENCE_PLATE_REGEX = "^([a-zA-Z]{2}\\d{2}[a-zA-Z]{3})$";

	private final
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
		return validateLicencePlateNumber(vehicle.getLicencePlate());
	}

	private static boolean validateLicencePlateNumber(final String licencePlate){
		return licencePlate.matches(LICENCE_PLATE_REGEX);
	}

}
