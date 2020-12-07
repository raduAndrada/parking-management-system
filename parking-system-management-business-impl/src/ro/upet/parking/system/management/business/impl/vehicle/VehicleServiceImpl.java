package ro.upet.parking.system.management.business.impl.vehicle;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.vehicle.VehicleService;
import ro.upet.parking.system.management.business.api.vehicle.VehicleValidator;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;
import ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository;
import ro.upet.parking.system.management.model.vehicle.Vehicle;

/**
 * @author Andrada 
 * Business level logic implementation for vehicles
 */
@Service
public class VehicleServiceImpl implements VehicleService {

	@Inject
	private VehicleRepository vehicleRepo;

	
	@Inject
	private  VehicleValidator vehicleValidator;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vehicle getById(final Long vehicleId) {
		return VehicleMapper.toVehicle(vehicleRepo.getOne(vehicleId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vehicle getByCode(final String vehicleCode) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Vehicle> getList() {
		return VehicleMapper.toVehicleList(vehicleRepo.findAll());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vehicle add(final Vehicle vehicle) throws BusinessException {
		final VehicleEntity entity = VehicleMapper.toVehicleEntity(vehicle);
		entity.setCreatedAt(Instant.now());
		entity.setUpdatedAt(Instant.now());
		if (vehicleValidator.validate(vehicle)) {
			final VehicleEntity savedEntity = vehicleRepo.save(entity);
			return VehicleMapper.toVehicle(savedEntity);
		} 
		throw new BusinessException("The licence plate for this vehicle is invalid");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vehicle update(final Vehicle vehicle) {
		final VehicleEntity entity = VehicleMapper.toVehicleEntity(vehicle);
		entity.setUpdatedAt(Instant.now());
		final VehicleEntity savedEntity = vehicleRepo.save(entity);
		return VehicleMapper.toVehicle(savedEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vehicle removeById(final Long vehicleId) throws BusinessException {
		final VehicleEntity entity = vehicleRepo.getOne(vehicleId);
		if (entity == null) {
			throw new BusinessException("The vehicle does not exist");
		}
		vehicleRepo.deleteById(vehicleId);
		return VehicleMapper.toVehicle(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vehicle removeByCode(final String vehicleCode) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Vehicle> findByUserUsername(String username) {
		return VehicleMapper.toVehicleList(vehicleRepo.findAllByUserUsename(username));
	}

}
