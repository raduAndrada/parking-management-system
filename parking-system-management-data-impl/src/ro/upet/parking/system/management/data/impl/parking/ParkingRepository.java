package ro.upet.parking.system.management.data.impl.parking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.upet.parking.system.management.data.api.parking.ParkingEntity;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingEntity, Long>{
}
