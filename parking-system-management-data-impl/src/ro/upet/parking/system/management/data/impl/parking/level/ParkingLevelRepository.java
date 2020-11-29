package ro.upet.parking.system.management.data.impl.parking.level;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;

@Repository
public interface ParkingLevelRepository extends JpaRepository <ParkingLevelEntity, Long> {
		
}
