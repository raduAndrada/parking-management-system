package ro.upet.parking.system.management.business.parking.spot;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.parking.spot.ParkingSpotService;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;

import java.util.List;

/**
 * @author Andrada
 * Business level logic implementation for parkingSpots 
 */
@Service
@AllArgsConstructor
public class ParkingSpotServiceImpl implements ParkingSpotService{
	
	final ParkingSpotRepository parkingSpotRepo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingSpot getById(final Long parkingSpotId) {
		return ParkingSpotMapper.toParkingSpot(parkingSpotRepo.getOne(parkingSpotId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingSpot getByCode(final String parkingSpotCode) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ParkingSpot> getList() {
		return ParkingSpotMapper.toParkingSpotList(parkingSpotRepo.findAll());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingSpot add(final ParkingSpot parkingSpot) {
		final ParkingSpotEntity entity = ParkingSpotMapper.toParkingSpotEntity(parkingSpot);
		final ParkingSpotEntity savedEntity = parkingSpotRepo.save(entity);
		return ParkingSpotMapper.toParkingSpot(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingSpot update(final ParkingSpot parkingSpot) {
		final ParkingSpotEntity entity = ParkingSpotMapper.toParkingSpotEntity(parkingSpot);
		final ParkingSpotEntity savedEntity = parkingSpotRepo.save(entity);
		return ParkingSpotMapper.toParkingSpot(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingSpot removeById(final Long parkingSpotId) throws BusinessException {
		final ParkingSpotEntity entity = parkingSpotRepo.getOne(parkingSpotId);
		if (entity == null ) {
			throw new BusinessException("Parking spot does not exist");
		}
		parkingSpotRepo.deleteById(parkingSpotId);
		return ParkingSpotMapper.toParkingSpot(entity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingSpot removeByCode(final String parkingSpotCode) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
