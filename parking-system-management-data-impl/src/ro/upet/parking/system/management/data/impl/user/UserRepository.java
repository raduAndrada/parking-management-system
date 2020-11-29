package ro.upet.parking.system.management.data.impl.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.upet.parking.system.management.data.api.user.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {	
	
}
