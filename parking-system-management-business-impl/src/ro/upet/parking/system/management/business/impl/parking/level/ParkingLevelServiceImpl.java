package ro.upet.parking.system.management.business.impl.parking.level;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ro.upet.parking.system.management.business.api.parking.level.ParkingLevelService;
import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;
import ro.upet.parking.system.management.data.impl.parking.ParkingRepository;
import ro.upet.parking.system.management.data.impl.parking.level.ParkingLevelRepository;
import ro.upet.parking.system.management.model.parking.level.ParkingLevel;

/**
 * @author Andrada
 * Business level logic implementation for parkingLevels 
 */
@Service
public class ParkingLevelServiceImpl implements ParkingLevelService{
	
	@Inject
	ParkingLevelRepository parkingLevelRepo;
	
	@Inject
	ParkingRepository parkingRepo;
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingLevel getParkingLevelById(final Long parkingLevelId) {
		return ParkingLevelMapper.toParkingLevel(parkingLevelRepo.getOne(parkingLevelId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingLevel getParkingLevelByCode(final String parkingLevelCode) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ParkingLevel> getParkingLevelList() {
		return ParkingLevelMapper.toParkingLevelList(parkingLevelRepo.findAll());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingLevel addParkingLevel(final ParkingLevel parkingLevel) {
		final ParkingLevelEntity entity = ParkingLevelMapper.toParkingLevelEntity(parkingLevel);
		entity.setParking(parkingRepo.getOne(parkingLevel.getParkingId()));
		entity.setCreatedAt(Instant.now());
		entity.setUpdatedAt(Instant.now());
		final ParkingLevelEntity savedEntity = parkingLevelRepo.save(entity);
		return ParkingLevelMapper.toParkingLevel(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingLevel updateParkingLevel(final ParkingLevel parkingLevel) {
		final ParkingLevelEntity entity = ParkingLevelMapper.toParkingLevelEntity(parkingLevel);
		entity.setParking(parkingRepo.getOne(parkingLevel.getParkingId()));
		entity.setUpdatedAt(Instant.now());
		final ParkingLevelEntity savedEntity = parkingLevelRepo.save(entity);
		return ParkingLevelMapper.toParkingLevel(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingLevel removeParkingLevelById(final Long parkingLevelId) throws Exception {
		final ParkingLevelEntity entity = parkingLevelRepo.getOne(parkingLevelId);
		if (entity == null ) {
			throw new Exception();
		}
		parkingLevelRepo.deleteById(parkingLevelId);
		return ParkingLevelMapper.toParkingLevel(entity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingLevel removeParkingLevelByCode(final String parkingLevelCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
