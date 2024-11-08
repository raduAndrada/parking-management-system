package ro.upet.parking.system.management.business.parking.zone;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.parking.zone.ParkingZoneService;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;
import ro.upet.parking.system.management.data.impl.parking.zone.ParkingZoneRepository;
import ro.upet.parking.system.management.model.parking.zone.ParkingZone;

import java.util.List;

/**
 * @author Andrada
 * Business level logic implementation for parkingZones 
 */
@Service
@AllArgsConstructor
public class ParkingZoneServiceImpl implements ParkingZoneService{


	private final ParkingZoneRepository parkingZoneRepo;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingZone getById(final Long parkingZoneId) {
		return ParkingZoneMapper.toParkingZone(parkingZoneRepo.getOne(parkingZoneId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingZone getByCode(final String parkingZoneCode) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ParkingZone> getList() {
		return ParkingZoneMapper.toParkingZoneList(parkingZoneRepo.findAll());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingZone add(final ParkingZone parkingZone) {
		final ParkingZoneEntity entity = ParkingZoneMapper.toParkingZoneEntity(parkingZone);
		final ParkingZoneEntity savedEntity = parkingZoneRepo.save(entity);
		return ParkingZoneMapper.toParkingZone(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingZone update(final ParkingZone parkingZone) {
		final ParkingZoneEntity entity = ParkingZoneMapper.toParkingZoneEntity(parkingZone);
		final ParkingZoneEntity savedEntity = parkingZoneRepo.save(entity);
		return ParkingZoneMapper.toParkingZone(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingZone removeById(final Long parkingZoneId) throws BusinessException {
		final ParkingZoneEntity entity = parkingZoneRepo.getOne(parkingZoneId);
		if (entity == null ) {
			throw new BusinessException("Parking zone does not exist");
		}
		parkingZoneRepo.deleteById(parkingZoneId);
		return ParkingZoneMapper.toParkingZone(entity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParkingZone removeByCode(final String parkingZoneCode) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
