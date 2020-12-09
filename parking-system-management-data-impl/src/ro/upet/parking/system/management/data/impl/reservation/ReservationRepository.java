package ro.upet.parking.system.management.data.impl.reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;
import ro.upet.parking.system.management.model.base.ReservationStatus;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
	
	public List<ReservationEntity> findAllByUserUsername(final String username);
	
	public List<ReservationEntity> findAllByReservationStatus(final ReservationStatus reservationStatus);
	
	public List<ReservationEntity> findAllByParkingSpotNumber(final String parkingSpotNumber);
	
}
