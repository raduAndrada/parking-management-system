package ro.upet.parking.system.management.business.impl.parking.zone;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ro.upet.parking.system.management.business.api.parking.zone.ParkingZoneService;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;
import ro.upet.parking.system.management.data.impl.parking.level.ParkingLevelRepository;
import ro.upet.parking.system.management.data.impl.parking.zone.ParkingZoneRepository;
import ro.upet.parking.system.management.model.parking.zone.ParkingZone;

/**
 * @author Andrada
 * Business level logic implementation for parkingZones 
 */
@Service
public class ParkingZoneServiceImpl implements ParkingZoneService{
	
	@Inject
	ParkingZoneRepository parkingZoneRepo;
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingZone getParkingZoneById(final Long parkingZoneId) {
		return ParkingZoneMapper.toParkingZone(parkingZoneRepo.getOne(parkingZoneId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingZone getParkingZoneByCode(final String parkingZoneCode) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ParkingZone> getParkingZoneList() {
		return ParkingZoneMapper.toParkingZoneList(parkingZoneRepo.findAll());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingZone addParkingZone(final ParkingZone parkingZone) {
		final ParkingZoneEntity entity = ParkingZoneMapper.toParkingZoneEntity(parkingZone);
		entity.setCreatedAt(Instant.now());
		entity.setUpdatedAt(Instant.now());
		final ParkingZoneEntity savedEntity = parkingZoneRepo.save(entity);
		return ParkingZoneMapper.toParkingZone(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingZone updateParkingZone(final ParkingZone parkingZone) {
		final ParkingZoneEntity entity = ParkingZoneMapper.toParkingZoneEntity(parkingZone);
		entity.setUpdatedAt(Instant.now());
		final ParkingZoneEntity savedEntity = parkingZoneRepo.save(entity);
		return ParkingZoneMapper.toParkingZone(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingZone removeParkingZoneById(final Long parkingZoneId) throws Exception {
		final ParkingZoneEntity entity = parkingZoneRepo.getOne(parkingZoneId);
		if (entity == null ) {
			throw new Exception();
		}
		parkingZoneRepo.deleteById(parkingZoneId);
		return ParkingZoneMapper.toParkingZone(entity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingZone removeParkingZoneByCode(final String parkingZoneCode) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
