package ro.upet.parking.system.management.rest.vehicle;

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

import ro.upet.parking.system.management.business.api.vehicle.VehicleService;
import ro.upet.parking.system.management.model.vehicle.Vehicle;

/**
 * @author Andrada
 * Rest controller for the vehicles
 */
@RestController
@RequestMapping(value = "/v1/vehicles")
@CrossOrigin(maxAge = 3600)
public class VehicleRest {

	private static final Logger LOGGER  = Logger.getLogger(VehicleRest.class.getName());
	
	@Inject
	private VehicleService vehicleService;
	
	/**
	 * @param code unique for each vehicle
	 * @return the vehicle with the corresponding code
	 */
	@GetMapping(path = "/code/{code}")
	public ResponseEntity<Vehicle> getVehicle(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to GET vehicle by code: %s", code));
		final Vehicle vehicle = vehicleService.getVehicleByCode(code);
		if (vehicle == null) {
			LOGGER.info(String.format("Vehicle with code: %s does not exist", code));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(vehicle);
		}
	}
	
	/**
	 * @param id of the vehicle
	 * @return the vehicle with the corresponding id
	 */
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<Vehicle> getVehicle(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to GET vehicle by id: %s", id));
		final Vehicle vehicle = vehicleService.getVehicleById(id);
		if (vehicle == null) {
			LOGGER.info(String.format("Vehicle with id: %s does not exist", id));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(vehicle);
		}
	}


	/**
	 * @return the list with all the vehicles
	 */
	@GetMapping
	public ResponseEntity<List<Vehicle>> getVehicles() {
		LOGGER.info(String.format("REST request to GET all vehicles"));
		final List<Vehicle> vehicleList= vehicleService.getVehicleList();
		if (vehicleList == null) {
			LOGGER.info(String.format("No vehicles found"));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(vehicleList);
		}
	}

	/**
	 * 
	 * @param vehicle the vehicle to be added
	 * @return the created entity
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<Vehicle> postVehicle(@RequestBody final Vehicle vehicle) {
		LOGGER.info(String.format("REST request to POST vehicle : %s", vehicle.toString()));
		final Vehicle createdVehicle;
		try {
			createdVehicle = vehicleService.addVehicle(vehicle);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong creating the vehicle : %s", vehicle.toString()));
			return null;
		}
		return ResponseEntity.ok(createdVehicle);
	}
	
	
	/**
	 * 
	 * @param vehicle the vehicle to be added
	 * @return the created entity
	 */
	@PutMapping
	@Transactional
	public ResponseEntity<Vehicle> putVehicle(@RequestBody final Vehicle vehicle) {
		LOGGER.info(String.format("REST request to PUT vehicle : %s", vehicle.toString()));
		final Vehicle updatedVehicle;
		try {
			updatedVehicle = vehicleService.updateVehicle(vehicle);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong updating the vehicle: %s", vehicle.toString()));
			return null;
		}
		return ResponseEntity.ok(updatedVehicle);
	}

	
	/**
	 * 
	 * @param id the identifier for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/id/{id}")
	@Transactional
	public ResponseEntity<Vehicle> deleteVehicle(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to DELETE vehicle with id : %s", id));
		final Vehicle deletedVehicle;
		try {
			deletedVehicle = vehicleService.removeVehicleById(id);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the vehicle with the id: %s", id));
			return null;
		}
		return ResponseEntity.ok(deletedVehicle);
	}
	
	/**
	 * 
	 * @param code the unique code for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/code/{code}")
	@Transactional
	public ResponseEntity<Vehicle> deleteVehicle(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to DELETE vehicle with code : %s", code));
		final Vehicle deletedVehicle;
		try {
			deletedVehicle = vehicleService.removeVehicleByCode(code);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the vehicle with the code: %s", code));
			return null;
		}
		return ResponseEntity.ok(deletedVehicle);
	}

}
