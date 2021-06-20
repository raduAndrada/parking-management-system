package ro.upet.parking.system.management.data.impl.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;
import ro.upet.parking.system.management.model.base.ReservationStatus;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    List<ReservationEntity> findAllByUserUsername(final String username);

    List<ReservationEntity> findAllByUserUsernameAndReservationStatus(final String username, final ReservationStatus reservationStatus);

    List<ReservationEntity> findAllByParkingSpotId(final Long parkingSpotId);

}
