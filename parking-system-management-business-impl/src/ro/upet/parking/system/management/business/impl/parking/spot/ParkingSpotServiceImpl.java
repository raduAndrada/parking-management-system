package ro.upet.parking.system.management.business.impl.parking.spot;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ro.upet.parking.system.management.business.api.parking.spot.ParkingSpotService;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.data.impl.parking.zone.ParkingZoneRepository;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;

/**
 * @author Andrada
 * Business level logic implementation for parkingSpots 
 */
@Service
public class ParkingSpotServiceImpl implements ParkingSpotService{
	
	@Inject
	ParkingSpotRepository parkingSpotRepo;
	
	@Inject
	ParkingZoneRepository parkingZoneRepo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingSpot getParkingSpotById(final Long parkingSpotId) {
		return ParkingSpotMapper.toParkingSpot(parkingSpotRepo.getOne(parkingSpotId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingSpot getParkingSpotByCode(final String parkingSpotCode) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ParkingSpot> getParkingSpotList() {
		return ParkingSpotMapper.toParkingSpotList(parkingSpotRepo.findAll());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingSpot addParkingSpot(final ParkingSpot parkingSpot) {
		final ParkingSpotEntity entity = ParkingSpotMapper.toParkingSpotEntity(parkingSpot);
		entity.setParkingZone(parkingZoneRepo.getOne(parkingSpot.getParkingZoneId()));
		final ParkingSpotEntity savedEntity = parkingSpotRepo.save(entity);
		return ParkingSpotMapper.toParkingSpot(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingSpot updateParkingSpot(final ParkingSpot parkingSpot) {
		final ParkingSpotEntity entity = ParkingSpotMapper.toParkingSpotEntity(parkingSpot);
		entity.setParkingZone(parkingZoneRepo.getOne(parkingSpot.getParkingZoneId()));
		final ParkingSpotEntity savedEntity = parkingSpotRepo.save(entity);
		return ParkingSpotMapper.toParkingSpot(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingSpot removeParkingSpotById(final Long parkingSpotId) throws Exception {
		final ParkingSpotEntity entity = parkingSpotRepo.getOne(parkingSpotId);
		if (entity == null ) {
			throw new Exception();
		}
		parkingSpotRepo.deleteById(parkingSpotId);
		return ParkingSpotMapper.toParkingSpot(entity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingSpot removeParkingSpotByCode(final String parkingSpotCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
