package ro.upet.parking.system.management.rest.vehicle;

import java.util.List;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.business.api.vehicle.VehicleService;
import ro.upet.parking.system.management.model.vehicle.Vehicle;
import ro.upet.parking.system.management.rest.base.BaseRest;

/**
 * @author Andrada
 * Rest controller for the vehicles
 */
@RestController
@RequestMapping(value = "/v1/vehicles")
@CrossOrigin(maxAge = 3600)
@Slf4j
public class VehicleRest extends BaseRest<Vehicle> {

	private final VehicleService service;

	public VehicleRest(VehicleService service) {
		this.service = service;
	}

	@Override
	@Inject
	public void setService(BaseService<Vehicle> service) {
		super.setService(this.service);
	}

	/**
	 * @param username of the user
	 * @return the list with all the reservations of a user
	 */
	@GetMapping("/user/{username}")
	public ResponseEntity<List<Vehicle>> getVehiclesForUser(@PathVariable final String username) {
		log.info("REST request to GET membership by userId : {}", username);
		final List<Vehicle> reservationList= service.findByUserUsername(username);
		if (reservationList == null) {
			log.info("No memberships found for the user with id: {}", username);
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(reservationList);
		}
	}
}
