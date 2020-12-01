package ro.upet.parking.system.management.business.api.user;

import java.util.List;

import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.model.user.User;

public interface UserService {	
	/**
	 * @param userId the id of the user searched
	 * @return the requested user
	 */
	public User getUserById(final Long userId);
	
	/**
	 * @param userCode the code of the user searched 
	 * @return the requested user
	 */
	public User getUserByCode(final String userCode);
	
	/**
	 * @return the list of all the users
	 */
	public List<User> getUserList();
	
	/**
	 * @param user the entity to be added
	 * @return the added entity
	 * @throws BusinessException if the username or email is taken
	 */
	public User addUser(final User user) throws BusinessException;
	
	/**
	 * @param user the updated user
	 * @return the updated entity
	 */
	public User updateUser(final User user);
	
	/**
	 * @param userId the id of the entity that will be deleted
	 * @return the deleted entity
	 * @throws BusinessException if the user does not exist
	 */
	public User removeUserById(final Long userId) throws BusinessException;
	
	/**
	 * @param userCode the code of the entity that will be deleted
	 * @return the deleted entity
	 */
	public User removeUserByCode(final String userCode);
}
