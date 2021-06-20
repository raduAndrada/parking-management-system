package ro.upet.parking.system.management.business.vehicle;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.vehicle.VehicleValidator;
import ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository;


@Service
@AllArgsConstructor
@Slf4j
public class VehicleValidatorImpl implements VehicleValidator{

	private final static String LICENCE_PLATE_REGEX = "^(?:[a-zA-Z]{1,2}\\d{2}[a-zA-Z]{3})$";

	private final
	VehicleRepository vehicleRepo;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validate(final String licencePlate) {
		if (vehicleRepo.findOptionalByLicencePlate(licencePlate).isPresent() || !validateLicencePlateNumber(licencePlate)) {
			log.error("Operation cannot be performed the {} is already taken or is invalid", licencePlate);
			throw new BusinessException("Invalid LicencePlateNumber");
		}

	}

	private static boolean validateLicencePlateNumber(final String licencePlate){
		return licencePlate.matches(LICENCE_PLATE_REGEX);
	}

}
