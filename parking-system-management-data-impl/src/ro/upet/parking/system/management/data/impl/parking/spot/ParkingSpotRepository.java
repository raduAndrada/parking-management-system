package ro.upet.parking.system.management.data.impl.parking.spot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotEntity, Long>{

}
