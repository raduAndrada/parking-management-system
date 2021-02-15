package ro.upet.parking.system.management.data.impl.membership;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ro.upet.parking.system.management.data.api.membership.MembershipEntity;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<MembershipEntity,Long> {

    List<MembershipEntity> findAllByUserId(final Long userId);
}
