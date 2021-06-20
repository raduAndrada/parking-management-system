package ro.upet.parking.system.management.business.impl.parking.zone;

import org.modelmapper.ModelMapper;
import ro.upet.parking.system.management.business.impl.parking.spot.ParkingSpotMapper;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;
import ro.upet.parking.system.management.model.parking.level.ParkingLevel;
import ro.upet.parking.system.management.model.parking.zone.ParkingZone;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author Andrada Mapper for the parkingZone entity and model
 */
public class ParkingZoneMapper {

	private static final ModelMapper MAPPER = new ModelMapper();

	/**
	 * @param parkingZone model for the parkingZone
	 * @return the corresponding entity
	 */
	public static ParkingZoneEntity toParkingZoneEntity(final ParkingZone parkingZone) {
		return MAPPER.map(parkingZone, ParkingZoneEntity.class);
	}

	/**
	 * @param entity database level parkingZone
	 * @return the model for the entity
	 */
	public static ParkingZone toParkingZone(final ParkingZoneEntity entity) {
		final List<ParkingSpotEntity> parkingZones = entity.getParkingSpots();
		entity.setParkingSpots(null);
		final ParkingZone parkingLevel = MAPPER.map(entity, ParkingZone.class);
		parkingLevel.setParkingSpots(ParkingSpotMapper.toParkingSpotList(parkingZones));
		return parkingLevel;
	}

	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<ParkingZone> toParkingZoneList(final List<ParkingZoneEntity> entityList) {
		return entityList.stream().map(ParkingZoneMapper::toParkingZone).collect(Collectors.toList());
	}
}
