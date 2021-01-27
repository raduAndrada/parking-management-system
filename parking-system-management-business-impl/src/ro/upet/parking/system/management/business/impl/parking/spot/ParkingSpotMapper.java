package ro.upet.parking.system.management.business.impl.parking.spot;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.model.parking.spot.ImtParkingSpot;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;

/**
 * 
 * @author Andrada
 * Mapper for the parkingSpot entity and model
 */
public class ParkingSpotMapper {

	/**
	 * @param parkingSpot model for the parkingSpots
	 * @return the corresponding entity
	 */
	public static ParkingSpotEntity toParkingSpotEntity(final ParkingSpot parkingSpot) {
		ParkingSpotEntity entity = new ParkingSpotEntity();
		entity.getBase().setCode(parkingSpot.getCode());
		entity.getBase().setId(parkingSpot.getId());
		entity.getBase().setCreatedAt(parkingSpot.getCreatedAt());
		entity.getBase().setUpdatedAt(parkingSpot.getUpdatedAt());
		entity.setNumber(parkingSpot.getNumber());
		entity.setAvailable(parkingSpot.isAvailable());
		entity.setRentable(parkingSpot.isRentable());
		entity.setRented(parkingSpot.isRented());
		setNullsToDefault(parkingSpot, entity);
		return entity;
	}
	
	/**
	 * @param entity database level parkingSpot
	 * @return the model for the entity
	 */
	public static ParkingSpot toParkingSpot(final ParkingSpotEntity entity) {
		return ImtParkingSpot.builder()
				.code(entity.getBase().getCode())
				.createdAt(entity.getBase().getCreatedAt())
				.id(entity.getBase().getId())
				.updatedAt(entity.getBase().getUpdatedAt())
				.number(entity.getNumber())
				.isRentable(entity.getRentable())
				.isAvailable(entity.getAvailable())
				.isRented(entity.getRented())
				.parkingZoneLetter(Objects.nonNull(entity.getParkingZone()) ? entity.getParkingZone().getLetter() : "")
				.parkingLevelNumber(Objects.nonNull(entity.getParkingZone()) 
						&& Objects.nonNull(entity.getParkingZone().getParkingLevel()) ?
						entity.getParkingZone().getParkingLevel().getNumber() : "")
				.parkingName(Objects.nonNull(entity.getParkingZone()) 
											&& Objects.nonNull(entity.getParkingZone().getParkingLevel())
											&& Objects.nonNull(entity.getParkingZone().getParkingLevel().getParking())
						? entity.getParkingZone().getParkingLevel().getParking().getName() : "")
				.build();
	}
	
	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<ParkingSpot> toParkingSpotList (final List<ParkingSpotEntity> entityList) {
		return entityList.stream().map(ParkingSpotMapper::toParkingSpot).collect(Collectors.toList());
	}
	
	private static void setNullsToDefault(final ParkingSpot parkingSpot, ParkingSpotEntity pse) {
		if (Objects.isNull(parkingSpot.isAvailable())) {
			pse.setAvailable(Boolean.TRUE);
		}
		
		if (Objects.isNull(parkingSpot.isRentable())) {
			pse.setRentable(Boolean.FALSE);
		}
	}
}
