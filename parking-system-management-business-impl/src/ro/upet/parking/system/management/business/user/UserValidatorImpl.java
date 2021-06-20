package ro.upet.parking.system.management.business.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.user.UserValidator;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.model.user.User;

@Service
@AllArgsConstructor
@Slf4j
public class UserValidatorImpl implements UserValidator {

	private final UserRepository userRepo;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validateUser(User user) {
		if (userRepo.findByUsername(user.getUsername()).isPresent() || userRepo.findByEmail(user.getEmail()).isPresent()) {
			log.info("CREATE cannot be performed, username {} or email {] are already taken", user.getUsername(), user.getEmail());
			throw new BusinessException("Invalid Email or Username");
		}
	}

	@Override
	public void validateEmail(String email) {
		if(userRepo.findByEmail(email).isPresent()){
			log.info("UPDATE on user  cannot be performed, email {] is already taken", email);
			throw new BusinessException("Invalid Email");
		}
	}

}
