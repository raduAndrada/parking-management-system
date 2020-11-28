package ro.utcn.parking.system.management.data.impl.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.utcn.parking.system.management.data.api.reservation.ReservationEntity;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
	
}
