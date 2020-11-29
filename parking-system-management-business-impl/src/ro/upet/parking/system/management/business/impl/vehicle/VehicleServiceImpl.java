package ro.upet.parking.system.management.business.impl.vehicle;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ro.upet.parking.system.management.business.api.vehicle.VehicleService;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository;
import ro.upet.parking.system.management.model.vehicle.Vehicle;

/**
 * @author Andrada 
 * Business level logic implementation for vehicles
 */
@Service
public class VehicleServiceImpl implements VehicleService {

	@Inject
	VehicleRepository vehicleRepo;


	@Inject
	ParkingSpotRepository parkingSpotRepo;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vehicle getVehicleById(final Long vehicleId) {
		return VehicleMapper.toVehicle(vehicleRepo.getOne(vehicleId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vehicle getVehicleByCode(final String vehicleCode) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Vehicle> getVehicleList() {
		return VehicleMapper.toVehicleList(vehicleRepo.findAll());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vehicle addVehicle(final Vehicle vehicle) {
		final VehicleEntity entity = VehicleMapper.toVehicleEntity(vehicle);
		entity.setCreatedAt(Instant.now());
		entity.setUpdatedAt(Instant.now());
		final VehicleEntity savedEntity = vehicleRepo.save(entity);
		return VehicleMapper.toVehicle(savedEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vehicle updateVehicle(final Vehicle vehicle) {
		final VehicleEntity entity = VehicleMapper.toVehicleEntity(vehicle);
		entity.setUpdatedAt(Instant.now());
		final VehicleEntity savedEntity = vehicleRepo.save(entity);
		return VehicleMapper.toVehicle(savedEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vehicle removeVehicleById(final Long vehicleId) throws Exception {
		final VehicleEntity entity = vehicleRepo.getOne(vehicleId);
		if (entity == null) {
			throw new Exception();
		}
		vehicleRepo.deleteById(vehicleId);
		return VehicleMapper.toVehicle(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vehicle removeVehicleByCode(final String vehicleCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
