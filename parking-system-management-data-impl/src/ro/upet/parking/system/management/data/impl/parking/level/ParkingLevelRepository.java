package ro.upet.parking.system.management.data.impl.parking.level;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;

@Repository
public interface ParkingLevelRepository extends JpaRepository <ParkingLevelEntity, Long> {
	
	@Query("SELECT pl FROM ParkingLevelEntity pl WHERE pl.parking.name= ?1")
	public List<ParkingLevelEntity> findAllByParkingName(final String parkingName);

}
