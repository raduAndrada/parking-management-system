package ro.upet.parking.system.management.rest.user;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.upet.parking.system.management.business.api.user.UserService;
import ro.upet.parking.system.management.model.user.User;

/**
 * @author Andrada
 * Rest controller for the users
 */
@RestController
@RequestMapping(value = "/v1/users")
@CrossOrigin(maxAge = 3600)
public class UserRest {
	
	private static final Logger LOGGER  = Logger.getLogger(UserRest.class.getName());

	@Inject
	private UserService userService;
	
	/**
	 * @param code unique for each user
	 * @return the user with the corresponding code
	 */
	@GetMapping(path = "/code/{code}")
	public ResponseEntity<User> getUser(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to GET user by code: %s", code));
		final User user = userService.getUserByCode(code);
		if (user == null) {
			LOGGER.info(String.format("User with code: %s does not exist", code));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(user);
		}
	}
	
	/**
	 * @param id of the user
	 * @return the user with the corresponding id
	 */
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<User> getUser(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to GET user by id: %s", id));
		final User user = userService.getUserById(id);
		if (user == null) {
			LOGGER.info(String.format("User with id: %s does not exist", id));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(user);
		}
	}


	/**
	 * @return the list with all the users
	 */
	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		LOGGER.info(String.format("REST request to GET all users"));
		final List<User> userList= userService.getUserList();
		if (userList == null) {
			LOGGER.info(String.format("No users found"));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(userList);
		}
	}

	/**
	 * 
	 * @param user the user to be added
	 * @return the created entity
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<User> postUser(@RequestBody final User user) {
		LOGGER.info(String.format("REST request to POST user : %s", user));
		final User createdUser;
		try {
			createdUser = userService.addUser(user);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong creating the user : %s", user));
			return null;
		}
		return ResponseEntity.ok(createdUser);
	}
	
	
	/**
	 * 
	 * @param user the user to be added
	 * @return the created entity
	 */
	@PutMapping
	@Transactional
	public ResponseEntity<User> putUser(@RequestBody final User user) {
		LOGGER.info(String.format("REST request to PUT user : %s", user));
		final User updatedUser;
		try {
			updatedUser = userService.updateUser(user);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong updating the user: %s", user));
			return null;
		}
		return ResponseEntity.ok(updatedUser);
	}

	
	/**
	 * 
	 * @param id the identifier for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/id/{id}")
	@Transactional
	public ResponseEntity<User> deleteUser(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to DELETE user with id : %s", id));
		final User deletedUser;
		try {
			deletedUser = userService.removeUserById(id);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the user with the id: %s", id));
			return null;
		}
		return ResponseEntity.ok(deletedUser);
	}
	
	/**
	 * 
	 * @param code the unique code for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/code/{code}")
	@Transactional
	public ResponseEntity<User> deleteUser(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to DELETE user with code : %s", code));
		final User deletedUser;
		try {
			deletedUser = userService.removeUserByCode(code);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the user with the code: %s", code));
			return null;
		}
		return ResponseEntity.ok(deletedUser);
	}

}
