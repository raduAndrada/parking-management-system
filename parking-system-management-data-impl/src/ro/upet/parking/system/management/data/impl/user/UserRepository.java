package ro.upet.parking.system.management.data.impl.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.upet.parking.system.management.data.api.user.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {	
	
	public Optional<UserEntity> findByUsernameOptional(String username);
	
	public Optional<UserEntity> findByEmailOptional(String email);
	
	public Optional<UserEntity> findByEmailAndPasswordAllIgnoreCaseOptional(String email, String password);
	
	public Optional<UserEntity> findByUsernameAndPasswordAllIgnoreCaseOptional(String username, String password);
	
}
