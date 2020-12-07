package ro.upet.parking.system.management.data.impl.reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
	
	public List<ReservationEntity> findAllByVehicleId(final Long vehicleId);
	
}
