package ro.utcn.parking.system.management.data.impl.membership;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.utcn.parking.system.management.data.api.membership.MembershipEntity;

@Repository
public interface MembershipRepository extends JpaRepository<MembershipEntity,Long> {
	
}
