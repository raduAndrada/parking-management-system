package ro.upet.parking.system.management.business.impl.user;

import javax.inject.Inject;

import ro.upet.parking.system.management.business.api.user.UserValidator;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.model.user.User;

public class UserValidatorImpl implements UserValidator {

	@Inject
	private UserRepository userRepo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validate(User user) {
		if (userRepo.findByUsernameOptional(user.getUsername()).isPresent() || userRepo.findByEmailOptional(user.getEmail()).isPresent()) {
			return false;
		}
		return true;
	}

}
