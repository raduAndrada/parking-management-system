package ro.upet.parking.system.management.business.impl.parking;

import java.util.List;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.data.api.parking.ParkingEntity;
import ro.upet.parking.system.management.model.parking.Parking;
import ro.utcn.parking.system.management.model.parking.ImtParking;

/**
 * 
 * @author Andrada
 * Mapper for the parking entity and model
 */
public class ParkingMapper {

	/**
	 * @param parking model for the parking
	 * @return the corresponding entity
	 */
	public static ParkingEntity toParkingEntity(final Parking parking) {
		final ParkingEntity entity = new ParkingEntity();
		entity.setCode(parking.getCode());
		entity.setId(parking.getId());
		entity.setCreatedAt(parking.getCreatedAt());
		entity.setUpdatedAt(parking.getUpdatedAt());
		entity.setLocation(parking.getLocation());
		entity.setOpensAt(parking.getOpensAt());
		entity.setClosesAt(parking.getClosesAt());
		entity.setName(parking.getName());
		return entity;
	}
	
	/**
	 * @param entity database level parking
	 * @return the model for the entity
	 */
	public static Parking toParking(final ParkingEntity entity) {
		return ImtParking.builder()
				.code(entity.getCode())
				.createdAt(entity.getCreatedAt())
				.id(entity.getId())
				.updatedAt(entity.getUpdatedAt())
				.name(entity.getName())
				.closesAt(entity.getClosesAt())
				.opensAt(entity.getOpensAt())
				.location(entity.getLocation())
				.name(entity.getName())
				.build();
	}
	
	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<Parking> toParkingList (final List<ParkingEntity> entityList) {
		return entityList.stream().map(ParkingMapper::toParking).collect(Collectors.toList());
	}
}
