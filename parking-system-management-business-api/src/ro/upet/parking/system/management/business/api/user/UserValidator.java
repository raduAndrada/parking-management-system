package ro.upet.parking.system.management.business.api.user;

import ro.upet.parking.system.management.model.user.User;

/**
 * @author Andrada
 * Validate a user
 */
public interface UserValidator {

	/**
	 * @param user the user to be validated
	 * @return true if the user is valid, false otherwise - a valid user requires the username not to be taken as well as the email address
	 */
	boolean validate(final User user);
}
