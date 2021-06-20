package ro.upet.parking.system.management.rest.user;

import com.stripe.model.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.business.api.user.UserService;
import ro.upet.parking.system.management.business.api.vehicle.VehicleService;
import ro.upet.parking.system.management.model.user.User;
import ro.upet.parking.system.management.model.user.UserCreate;
import ro.upet.parking.system.management.model.user.UserUpdate;
import ro.upet.parking.system.management.rest.base.BaseRest;
import ro.upet.parking.system.management.rest.stripe.UserCreateStripeService;

import javax.inject.Inject;

/**
 * @author Andrada Rest controller for the users
 */
@RestController
@RequestMapping(value = "/v1/users")
@Slf4j
@AllArgsConstructor
@CrossOrigin(maxAge = 3600)public class UserRest extends BaseRest<User> {

	private final UserService userService;

	private final VehicleService vehicleService;

	private final UserCreateStripeService stripeService;


	@Override
	@Inject
	public void setService(BaseService<User> service) {
		super.setService(this.userService);
	}
	
	/**
	 * @param username of the entity
	 * @return the entity with the corresponding username
	 */
	@GetMapping(path = USER_USERNAME_PATH)
	public ResponseEntity<User> get(@PathVariable final String username) {
		log.info("REST request to GET entity by username: {}", username);
		final User entity = userService.getByUsername(username);
		if (entity == null) {
			log.info(" with id: {} does not exist", username);
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(entity);
		}
	}
	
	/**
	 * 
	 * @param user the entity to be added
	 * @return the created entity
	 */
	@PostMapping("/login")
	public ResponseEntity<User> post(@RequestBody final User user) {
		log.info("REST request to login : {}", user.toString());
		User validUser;
		try {
			validUser = userService.loginWithUsernameAndPassword(user.getUsername(), user.getPassword());
			if (validUser == null) {
				validUser = userService.loginWithEmailAndPassword(user.getUsername(), user.getPassword());
			}
		} catch (final Exception e) {
			log.info("Something went wrong logging in the user : {}", user, e);
			return null;
		}
	//	SecurityContextHolder.getContext().setAuthentication(new AuthenticationImpl(validUser));
		return ResponseEntity.ok(validUser);
	}
	
	/**
	 * 
	 * @param userCreate the entity to be added
	 * @return the created entity
	 */
	@PostMapping("/customer-create")
	public ResponseEntity<User> createUser(@RequestBody final UserCreate userCreate) {
		log.info("REST request to CREATE User : {}", userCreate.toString());
		final User created;
		try {
			created = vehicleService.add(userCreate.getVehicle()).getUser();
			Customer customer = stripeService.createCustomer(created.getEmail(), created.getName());
			stripeService.createCard(customer.getId(), userCreate);
		} catch (final Exception e) {
			log.info("Something went wrong creating the entity : {}", userCreate, e);
			return null;
		}
		return ResponseEntity.ok(created);
	}
	
	
	/**
	 * 
	 * @param userUpdate the entity to be updated
	 * @return the created entity
	 */
	@PutMapping("/customer-update")
	public ResponseEntity<User> updateUser(@RequestBody final UserUpdate userUpdate) {
		log.info("REST request to UPDATE User : {}", userUpdate.toString());
		final User updated;
		try {
			updated = userService.update(userUpdate);
			//TODO stripe for credit card
		} catch (final Exception e) {
			log.info("Something went wrong UPDATING the entity : {}", userUpdate);
			return null;
		}
		return ResponseEntity.ok(updated);
	}

}
