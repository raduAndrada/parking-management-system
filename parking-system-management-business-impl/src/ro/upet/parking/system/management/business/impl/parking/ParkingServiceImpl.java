package ro.upet.parking.system.management.business.impl.parking;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.parking.ParkingService;
import ro.upet.parking.system.management.data.api.parking.ParkingEntity;
import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;
import ro.upet.parking.system.management.data.impl.parking.ParkingRepository;
import ro.upet.parking.system.management.data.impl.parking.level.ParkingLevelRepository;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.data.impl.parking.zone.ParkingZoneRepository;
import ro.upet.parking.system.management.model.parking.Parking;
import ro.upet.parking.system.management.model.parking.ParkingCreate;

/**
 * @author Andrada
 * Business level logic implementation for parkings 
 */
@Service
public class ParkingServiceImpl implements ParkingService{
	
	@Inject
	private ParkingRepository parkingRepo;
	
	@Inject
	private ParkingLevelRepository parkingLevelRepo;
	
	@Inject
	private ParkingZoneRepository parkingZoneRepo;
	
	@Inject
	private ParkingSpotRepository parkingSpotRepo;

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
	public Parking removeParkingById(final Long parkingId) throws BusinessException {
		final ParkingEntity entity = parkingRepo.getOne(parkingId);
		if (entity == null ) {
			throw new BusinessException("Parking does not exist");
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public ParkingCreate configureParking(final ParkingCreate parkingCreate) {
		final ParkingEntity parking = ParkingMapper.toParkingEntity(addParking(parkingCreate.getParking()));
		final Instant now = Instant.now();
		for (int level = 0; level < parkingCreate.getNumberOfLevels(); level ++) {
		   final ParkingLevelEntity ple = new ParkingLevelEntity();
		        ple.setCreatedAt(now);
		        ple.setUpdatedAt(now);
		        ple.setNumber("" + level);
		        ple.setParking(parking);
		        
		        final List<ParkingZoneEntity> parkingZones = Lists.newArrayList();
			for(char zone = Character.toUpperCase(parkingCreate.getParkingZoneStartingLetter()); zone <= Character.toUpperCase(parkingCreate.getParkingZoneEndingLetter()); zone++ )
		    {
				final ParkingZoneEntity pze = new ParkingZoneEntity();
				pze.setLetter("" + zone);
				pze.setCreatedAt(now);
				pze.setUpdatedAt(now);
				final List<ParkingSpotEntity> parkingSpots = Lists.newArrayList();
				for (int spot= 0; spot < parkingCreate.getParkingZoneSpotNumber(); spot++) {
					final ParkingSpotEntity pse = new ParkingSpotEntity();
					pse.setNumber("" + zone + spot);
					pse.setAvailable(Boolean.TRUE);
					pse.setRentable(Boolean.FALSE);
					pse.setRented(Boolean.FALSE);
					pse.setCreatedAt(now);
					pse.setUpdatedAt(now);
					final ParkingSpotEntity savedPse = parkingSpotRepo.save(pse);
					parkingSpots.add(savedPse);
				}
				pze.setParkingSpots(parkingSpots);
				final ParkingZoneEntity savedPze = parkingZoneRepo.save(pze);
				parkingZones.add(savedPze);
		    }
			ple.setParkingZones(parkingZones);
			parkingLevelRepo.save(ple);
		}
		
		return parkingCreate;
	}
	
	
}
