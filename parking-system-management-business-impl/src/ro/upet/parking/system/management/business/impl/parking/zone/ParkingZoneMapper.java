package ro.upet.parking.system.management.business.impl.parking.zone;

import java.util.List;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;
import ro.upet.parking.system.management.model.parking.zone.ImtParkingZone;
import ro.upet.parking.system.management.model.parking.zone.ParkingZone;

/**
 * 
 * @author Andrada
 * Mapper for the parkingZone entity and model
 */
public class ParkingZoneMapper {

	/**
	 * @param parkingZone model for the parkingZone
	 * @return the corresponding entity
	 */
	public static ParkingZoneEntity toParkingZoneEntity(final ParkingZone parkingZone) {
		final ParkingZoneEntity entity = new ParkingZoneEntity();
		entity.setCode(parkingZone.getCode());
		entity.setId(parkingZone.getId());
		entity.setCreatedAt(parkingZone.getCreatedAt());
		entity.setUpdatedAt(parkingZone.getUpdatedAt());
		entity.setLetter(parkingZone.getLetter());
		return entity;
	}
	
	/**
	 * @param entity database level parkingZone
	 * @return the model for the entity
	 */
	public static ParkingZone toParkingZone(final ParkingZoneEntity entity) {
		return ImtParkingZone.builder()
				.code(entity.getCode())
				.createdAt(entity.getCreatedAt())
				.id(entity.getId())
				.parkingLevelCode(entity.getParkingLevel().getCode())
				.parkingLevelId(entity.getParkingLevel().getId())
				.updatedAt(entity.getUpdatedAt())
				.letter(entity.getLetter())
				.build();
	}
	
	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<ParkingZone> toParkingZoneList (final List<ParkingZoneEntity> entityList) {
		return entityList.stream().map(ParkingZoneMapper::toParkingZone).collect(Collectors.toList());
	}
}
