package ro.upet.parking.system.management.business.api.user;

import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.model.user.User;
import ro.upet.parking.system.management.model.user.UserUpdate;

/**
 * @author Andrada
 * Functionality for a user
 */
public interface UserService extends BaseService<User> {	

	/**
	 * @param username the username of the user
	 * @param password the password for the user 
	 * @return the user if it exists, empty if not
	 */
	public User loginWithUsernameAndPassword(final String username,final String password);
	
	/**
	 * @param email the email of the user
	 * @param password the password for the user 
	 * @return the user if it exists, empty if not
	 */
	public User loginWithEmailAndPassword(final String email,final String password);
	
	/**
	 * @param userUpdate the details of the userUpdate
	 * @return the updated user
	 */
	public User update(final UserUpdate userUpdate) ;

	/**
	 * @param username the username of the user searched for
	 * @return the requested used
	 */
	public User getByUsername(String username);

}
