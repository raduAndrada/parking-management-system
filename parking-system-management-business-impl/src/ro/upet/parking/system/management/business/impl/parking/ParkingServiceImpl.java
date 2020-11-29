package ro.upet.parking.system.management.business.impl.parking;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ro.upet.parking.system.management.business.api.parking.ParkingService;
import ro.upet.parking.system.management.data.api.parking.ParkingEntity;
import ro.upet.parking.system.management.data.impl.parking.ParkingRepository;
import ro.upet.parking.system.management.model.parking.Parking;

/**
 * @author Andrada
 * Business level logic implementation for parkings 
 */
@Service
public class ParkingServiceImpl implements ParkingService{
	
	@Inject
	ParkingRepository parkingRepo;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Parking getParkingById(final Long parkingId) {
		return ParkingMapper.toParking(parkingRepo.getOne(parkingId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Parking getParkingByCode(final String parkingCode) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Parking> getParkingList() {
		return ParkingMapper.toParkingList(parkingRepo.findAll());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Parking addParking(final Parking parking) {
		final ParkingEntity entity = ParkingMapper.toParkingEntity(parking);
		entity.setCreatedAt(Instant.now());
		entity.setUpdatedAt(Instant.now());
		final ParkingEntity savedEntity = parkingRepo.save(entity);
		return ParkingMapper.toParking(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Parking updateParking(final Parking parking) {
		final ParkingEntity entity = ParkingMapper.toParkingEntity(parking);
		entity.setUpdatedAt(Instant.now());
		final ParkingEntity savedEntity = parkingRepo.save(entity);
		return ParkingMapper.toParking(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Parking removeParkingById(final Long parkingId) throws Exception {
		final ParkingEntity entity = parkingRepo.getOne(parkingId);
		if (entity == null ) {
			throw new Exception();
		}
		parkingRepo.deleteById(parkingId);
		return ParkingMapper.toParking(entity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Parking removeParkingByCode(final String parkingCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
