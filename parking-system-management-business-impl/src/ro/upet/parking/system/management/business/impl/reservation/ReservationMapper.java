package ro.upet.parking.system.management.business.impl.reservation;

import java.util.List;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;
import ro.upet.parking.system.management.model.reservation.Reservation;
import ro.utcn.parking.system.management.model.reservation.ImtReservation;

/**
 * 
 * @author Andrada
 * Mapper for the reservation entity and model
 */
public class ReservationMapper {

	/**
	 * @param reservation model for the reservation
	 * @return the corresponding entity
	 */
	public static ReservationEntity toReservationEntity(final Reservation reservation) {
		final ReservationEntity entity = new ReservationEntity();
		entity.setCode(reservation.getCode());
		entity.setId(reservation.getId());
		entity.setCreatedAt(reservation.getCreatedAt());
		entity.setUpdatedAt(reservation.getUpdatedAt());
		entity.setEndTime(reservation.getEndTime());
		entity.setNotes(reservation.getNotes());
		entity.setStartTime(reservation.getStartTime());
		entity.setVehicleLicencePlate(reservation.getVehicleLicencePlate());
		return entity;
	}
	
	/**
	 * @param entity database level reservation
	 * @return the model for the entity
	 */
	public static Reservation toReservation(final ReservationEntity entity) {
		return ImtReservation.builder()
				.code(entity.getCode())
				.createdAt(entity.getCreatedAt())
				.id(entity.getId())
				.parkingSpotCode(entity.getParkingSpot().getCode())
				.parkingSpotId(entity.getParkingSpot().getId())
				.updatedAt(entity.getUpdatedAt())
				.endTime(entity.getEndTime())
				.startTime(entity.getStartTime())
				.reservationStatus(entity.getReservationStatus())
				.vehicleCode(entity.getVehicle().getCode())
				.vehicleId(entity.getVehicle().getId())
				.vehicleLicencePlate(entity.getVehicleLicencePlate())
				.build();
	}
	
	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<Reservation> toReservationList (final List<ReservationEntity> entityList) {
		return entityList.stream().map(ReservationMapper::toReservation).collect(Collectors.toList());
	}
}
