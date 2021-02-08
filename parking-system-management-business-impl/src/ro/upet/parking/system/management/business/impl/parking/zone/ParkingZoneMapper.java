package ro.upet.parking.system.management.business.impl.parking.zone;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.business.impl.base.GenericMapper;
import ro.upet.parking.system.management.business.impl.parking.spot.ParkingSpotMapper;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;
import ro.upet.parking.system.management.model.parking.level.ImtParkingLevel;
import ro.upet.parking.system.management.model.parking.zone.ImtParkingZone;
import ro.upet.parking.system.management.model.parking.zone.MdfParkingZone;
import ro.upet.parking.system.management.model.parking.zone.ParkingZone;

/**
 * 
 * @author Andrada Mapper for the parkingZone entity and model
 */
public class ParkingZoneMapper {

	private static final GenericMapper<ParkingZoneEntity, ParkingZone> MAPPER = new GenericMapper<ParkingZoneEntity, ParkingZone>();

	/**
	 * @param parkingZone model for the parkingZone
	 * @return the corresponding entity
	 */
	public static ParkingZoneEntity toParkingZoneEntity(final ParkingZone parkingZone) {
		final ParkingZoneEntity entity = new ParkingZoneEntity();
		MAPPER.mapToEntity(parkingZone, entity);
		return entity;
	}

	/**
	 * @param entity database level parkingZone
	 * @return the model for the entity
	 */
	public static ParkingZone toParkingZone(final ParkingZoneEntity entity) {
		MdfParkingZone model = MdfParkingZone.create();
		MAPPER.mapToModel(entity, model);
		model.setParkingSpots((Objects.nonNull(entity.getParkingSpots()) && !entity.getParkingSpots().isEmpty())
				? ParkingSpotMapper.toParkingSpotList(entity.getParkingSpots())
				: null);
		return model.toImmutable();
	}

	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<ParkingZone> toParkingZoneList(final List<ParkingZoneEntity> entityList) {
		return entityList.stream().map(ParkingZoneMapper::toParkingZone).collect(Collectors.toList());
	}
}
