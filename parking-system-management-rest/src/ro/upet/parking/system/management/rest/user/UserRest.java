package ro.upet.parking.system.management.rest.user;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.business.api.user.UserService;
import ro.upet.parking.system.management.business.api.vehicle.VehicleService;
import ro.upet.parking.system.management.model.user.User;
import ro.upet.parking.system.management.model.user.UserCreate;
import ro.upet.parking.system.management.model.user.UserUpdate;
import ro.upet.parking.system.management.rest.base.BaseRest;

/**
 * @author Andrada Rest controller for the users
 */
@RestController
@RequestMapping(value = "/v1/users")
@CrossOrigin(maxAge = 3600)
public class UserRest extends BaseRest<User> {

	@Inject
	private UserService userService;
	

	@Inject
	private VehicleService vehicleService;


	@Override
	@Inject
	public void setService(BaseService<User> service) {
		super.setService(this.userService);
	}
	
	/**
	 * @param id of the entity
	 * @return the entity with the corresponding id
	 */
	@GetMapping(path = USER_USERNAME_PATH)
	public ResponseEntity<User> get(@PathVariable final String username) {
		LOGGER.info(String.format("REST request to GET entity by username: %s", username));
		final User entity = userService.getByUsername(username);
		if (entity == null) {
			LOGGER.info(String.format(" with id: %s does not exist", username));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(entity);
		}
	}
	
	/**
	 * 
	 * @param entity the entity to be added
	 * @return the created entity
	 */
	@PostMapping("/login")
	@Transactional
	public ResponseEntity<User> post(@RequestBody final User user) {
		LOGGER.info(String.format("REST request to login : %s", user.toString()));
		User validUser;
		try {
			validUser = userService.loginWithUsernameAndPassword(user.getUsername(), user.getPassword());
			if (validUser == null) {
				validUser = userService.loginWithEmailAndPassword(user.getUsername(), user.getPassword());
			}
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong loggin in the user : %s", user));
			return null;
		}
	//	SecurityContextHolder.getContext().setAuthentication(new AuthenticationImpl(validUser));
		return ResponseEntity.ok(validUser);
	}
	
	/**
	 * 
	 * @param entity the entity to be added
	 * @return the created entity
	 */
	@PostMapping("/customer-create")
	@Transactional
	public ResponseEntity<User> createUser(@RequestBody final UserCreate userCreate) {
		LOGGER.info(String.format("REST request to CREATE User : %s", userCreate.toString()));
		final User created;
		try {
			created = vehicleService.add(userCreate.getVehicle()).getUser();
			//TODO stripe for credit card
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong creating the entity : %s", userCreate));
			return null;
		}
		return ResponseEntity.ok(created);
	}
	
	
	/**
	 * 
	 * @param entity the entity to be added
	 * @return the created entity
	 */
	@PutMapping("/customer-update")
	@Transactional
	public ResponseEntity<User> updateUser(@RequestBody final UserUpdate userUpdate) {
		LOGGER.info(String.format("REST request to UPDATE User : %s", userUpdate.toString()));
		final User updated;
		try {
			updated = userService.update(userUpdate);
			//TODO stripe for credit card
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong UPDATING the entity : %s", userUpdate));
			return null;
		}
		return ResponseEntity.ok(updated);
	}

}
