package ro.upet.parking.system.management.business.impl.reservation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.business.impl.parking.spot.ParkingSpotMapper;
import ro.upet.parking.system.management.business.impl.user.UserMapper;
import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;
import ro.upet.parking.system.management.model.reservation.ImtReservation;
import ro.upet.parking.system.management.model.reservation.Reservation;

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
		entity.setUser(Objects.nonNull(entity.getUser()) ? UserMapper.toUserEntity(reservation.getUser()) : null);
		entity.setReservationStatus(reservation.getReservationStatus());
		entity.setParkingSpot(Objects.nonNull(entity.getParkingSpot()) ? ParkingSpotMapper.toParkingSpotEntity(reservation.getParkingSpot()) : null);
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
				.parkingSpot(ParkingSpotMapper.toParkingSpot(entity.getParkingSpot()))
				.updatedAt(entity.getUpdatedAt())
				.endTime(entity.getEndTime())
				.startTime(entity.getStartTime())
				.reservationStatus(entity.getReservationStatus())
				.user(Objects.nonNull(entity.getUser()) ? UserMapper.toUser(entity.getUser()) : null)
				.notes(entity.getNotes())
				.reservationStatus(entity.getReservationStatus())
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
