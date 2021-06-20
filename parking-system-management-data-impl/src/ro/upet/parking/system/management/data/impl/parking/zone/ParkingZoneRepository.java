package ro.upet.parking.system.management.data.impl.parking.zone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;

import java.util.List;

@Repository
public interface ParkingZoneRepository extends JpaRepository<ParkingZoneEntity, Long> {

    List<ParkingZoneEntity> findAllByParkingLevelId(final Long parkingLevelId);
}
