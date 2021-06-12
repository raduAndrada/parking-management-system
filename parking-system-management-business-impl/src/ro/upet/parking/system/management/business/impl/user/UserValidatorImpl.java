package ro.upet.parking.system.management.business.impl.user;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import ro.upet.parking.system.management.business.api.user.UserValidator;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.model.user.User;

@Service
public class UserValidatorImpl implements UserValidator {

	private final UserRepository userRepo;

	public UserValidatorImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validate(User user) {
		if (userRepo.findByUsername(user.getUsername()).isPresent() || userRepo.findByEmail(user.getEmail()).isPresent()) {
			return false;
		}
		return true;
	}

}
