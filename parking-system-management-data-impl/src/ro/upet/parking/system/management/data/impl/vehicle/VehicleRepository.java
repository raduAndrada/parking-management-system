package ro.upet.parking.system.management.data.impl.vehicle;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long>{
	
	public Optional<VehicleEntity> findOptionalByLicencePlate(final String licencePlate);
	
	public List<VehicleEntity> findAllByUserUsename(final String username);

}
