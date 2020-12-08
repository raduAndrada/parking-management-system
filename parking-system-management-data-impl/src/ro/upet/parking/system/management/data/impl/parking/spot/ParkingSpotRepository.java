package ro.upet.parking.system.management.data.impl.parking.spot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotEntity, Long>{
	
	
	@Query("SELECT ps FROM ParkingSpotEntity ps WHERE ps.parkingZone.parkingLevel.parking.name = ?1 and ps.available = true")
	public List<ParkingSpotEntity> findAllAvailableByParkingName(final String parkingName);
	
	public List<ParkingSpotEntity> findAllByAvailable(final boolean available);
}
