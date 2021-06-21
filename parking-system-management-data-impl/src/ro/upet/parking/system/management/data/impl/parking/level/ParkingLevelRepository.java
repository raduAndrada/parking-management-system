package ro.upet.parking.system.management.data.impl.parking.level;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;

import java.util.List;

@Repository
public interface ParkingLevelRepository extends JpaRepository <ParkingLevelEntity, Long> {
	
	@Query("SELECT pl FROM parking_levels pl WHERE pl.parking.name= ?1")
	List<ParkingLevelEntity> findAllByParkingName(final String parkingName);

	List<ParkingLevelEntity> findAllByParkingId(final Long parkingId);
}
