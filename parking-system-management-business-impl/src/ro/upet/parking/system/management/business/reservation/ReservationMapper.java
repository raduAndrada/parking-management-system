package ro.upet.parking.system.management.business.reservation;

import org.modelmapper.ModelMapper;
import ro.upet.parking.system.management.business.parking.spot.ParkingSpotMapper;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;
import ro.upet.parking.system.management.model.reservation.Reservation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 
 * @author Andrada
 * Mapper for the reservation entity and model
 */
public class ReservationMapper {

	private static final ModelMapper MAPPER = new ModelMapper();

	/**
	 * @param reservation model for the reservation
	 * @return the corresponding entity
	 */
	public static ReservationEntity toReservationEntity(final Reservation reservation) {
		final ReservationEntity entity = MAPPER.map(reservation, ReservationEntity.class);
		entity.setParkingSpot(Objects.nonNull(entity.getParkingSpot()) ? ParkingSpotMapper.toParkingSpotEntity(reservation.getParkingSpot()) : null);
		return entity;
	}
	
	/**
	 * @param entity database level reservation
	 * @return the model for the entity
	 */
	public static Reservation toReservation(final ReservationEntity entity) {
		final ParkingSpotEntity parkingSpot = entity.getParkingSpot();
		entity.setParkingSpot(null);
		Reservation reservation = MAPPER.map(entity, Reservation.class);
		 reservation.setParkingSpot(ParkingSpotMapper.toParkingSpot(parkingSpot));
		 return reservation;
	}
	
	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<Reservation> toReservationList (final List<ReservationEntity> entityList) {
		return entityList.stream().map(ReservationMapper::toReservation).collect(Collectors.toList());
	}
}
