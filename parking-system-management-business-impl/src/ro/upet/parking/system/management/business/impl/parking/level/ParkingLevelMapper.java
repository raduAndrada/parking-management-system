package ro.upet.parking.system.management.business.impl.parking.level;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.business.impl.base.GenericMapper;
import ro.upet.parking.system.management.business.impl.parking.ParkingMapper;
import ro.upet.parking.system.management.business.impl.parking.zone.ParkingZoneMapper;
import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;
import ro.upet.parking.system.management.model.parking.level.MdfParkingLevel;
import ro.upet.parking.system.management.model.parking.level.ParkingLevel;

/**
 * 
 * @author Andrada Mapper for the parkingLevel entity and model
 */
public class ParkingLevelMapper {

	private static final GenericMapper<ParkingLevelEntity, ParkingLevel> MAPPER = new GenericMapper();

	/**
	 * @param parkingLevel model for the parkingLevel
	 * @return the corresponding entity
	 */
	public static ParkingLevelEntity toParkingLevelEntity(final ParkingLevel parkingLevel) {
		final ParkingLevelEntity entity = new ParkingLevelEntity();
		MAPPER.mapToEntity(parkingLevel, entity);
		entity.setParking(ParkingMapper.toParkingEntity(parkingLevel.getParking()));
		return entity;
	}

	/**
	 * @param entity database level parkingLevel
	 * @return the model for the entity
	 */
	public static ParkingLevel toParkingLevel(final ParkingLevelEntity entity) {
		MdfParkingLevel model = MdfParkingLevel.create();
		MAPPER.mapToModel(entity, model);
		model.setParkingZones(Objects.nonNull(entity.getParkingZones()) && !entity.getParkingZones().isEmpty()
				? ParkingZoneMapper.toParkingZoneList(entity.getParkingZones())
				: null);
		return model.toImmutable();
	}

	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<ParkingLevel> toParkingLevelList(final List<ParkingLevelEntity> entityList) {
		return entityList.stream().map(ParkingLevelMapper::toParkingLevel).collect(Collectors.toList());
	}
}
