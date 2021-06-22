package ro.upet.parking.system.management.business.api.user;

import ro.upet.parking.system.management.model.user.User;

/**
 * @author Andrada
 * Validate a user
 */
public interface UserValidator {

	/**
	 * @param user the user to be validated
	 * @throws ro.upet.parking.system.management.business.api.core.BusinessException if the email or username is taken
	 */
	void validateUser(final User user);

	/**
	 * @param email of the user to be validated
	 * @throws ro.upet.parking.system.management.business.api.core.BusinessException if the email is taken
	 */
	void validateEmail(final String email);
}
