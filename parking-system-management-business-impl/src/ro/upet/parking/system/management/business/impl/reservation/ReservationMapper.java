package ro.upet.parking.system.management.business.impl.reservation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.business.impl.base.GenericMapper;
import ro.upet.parking.system.management.business.impl.parking.spot.ParkingSpotMapper;
import ro.upet.parking.system.management.business.impl.user.UserMapper;
import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;
import ro.upet.parking.system.management.model.reservation.MdfReservation;
import ro.upet.parking.system.management.model.reservation.Reservation;

/**
 * 
 * @author Andrada
 * Mapper for the reservation entity and model
 */
public class ReservationMapper {
	
	private static final GenericMapper<ReservationEntity, Reservation> MAPPER = new GenericMapper();

	/**
	 * @param reservation model for the reservation
	 * @return the corresponding entity
	 */
	public static ReservationEntity toReservationEntity(final Reservation reservation) {
		final ReservationEntity entity = new ReservationEntity();
		MAPPER.mapToEntity(reservation, entity);
		entity.setParkingSpot(Objects.nonNull(entity.getParkingSpot()) ? ParkingSpotMapper.toParkingSpotEntity(reservation.getParkingSpot()) : null);
		return entity;
	}
	
	/**
	 * @param entity database level reservation
	 * @return the model for the entity
	 */
	public static Reservation toReservation(final ReservationEntity entity) {
		MdfReservation model = MdfReservation.create();
		MAPPER.mapToModel(entity, model);
		model.setParkingSpot(ParkingSpotMapper.toParkingSpot(entity.getParkingSpot()));
		model.setUser(UserMapper.toUser(entity.getUser()));
		return model.toImmutable();
	}
	
	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<Reservation> toReservationList (final List<ReservationEntity> entityList) {
		return entityList.stream().map(ReservationMapper::toReservation).collect(Collectors.toList());
	}
}
