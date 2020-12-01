package ro.upet.parking.system.management.rest.parking;

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

import ro.upet.parking.system.management.business.api.parking.ParkingService;
import ro.upet.parking.system.management.model.parking.Parking;
import ro.upet.parking.system.management.model.parking.ParkingCreate;
import ro.upet.parking.system.management.rest.parking.ParkingRest;

/**
 * @author Andrada
 * Rest controller for the parkings
 */
@RestController
@RequestMapping(value = "/v1/parkings")
@CrossOrigin(maxAge = 3600)
public class ParkingRest {

	private static final Logger LOGGER  = Logger.getLogger(ParkingRest.class.getName());
	
	@Inject
	private ParkingService parkingService;
	
	/**
	 * @param code unique for each parking
	 * @return the parking with the corresponding code
	 */
	@GetMapping(path = "/code/{code}")
	public ResponseEntity<Parking> getParking(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to GET parking by code: %s", code));
		final Parking parking = parkingService.getParkingByCode(code);
		if (parking == null) {
			LOGGER.info(String.format("Parking with id: %s does not exist", code));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(parking);
		}
	}
	
	/**
	 * @param id of the parking
	 * @return the parking with the corresponding id
	 */
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<Parking> getParking(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to GET parking by id: %s", id));
		final Parking parking = parkingService.getParkingById(id);
		if (parking == null) {
			LOGGER.info(String.format("Parking with id: %s does not exist", id));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(parking);
		}
	}


	/**
	 * @return the list with all the parkings
	 */
	@GetMapping
	public ResponseEntity<List<Parking>> getParkings() {
		LOGGER.info(String.format("REST request to GET all parkings"));
		final List<Parking> parkingList= parkingService.getParkingList();
		if (parkingList == null) {
			LOGGER.info(String.format("No parkings found"));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(parkingList);
		}
	}

	/**
	 * 
	 * @param parking the parking to be added
	 * @return the created entity
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<Parking> postParking(@RequestBody final Parking parking) {
		LOGGER.info(String.format("REST request to POST parking : %s", parking));
		final Parking createdParking;
		try {
			createdParking = parkingService.addParking(parking);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong creating the parking : %s", parking));
			return null;
		}
		return ResponseEntity.ok(createdParking);
	}
	
	/**
	 * 
	 * @param parkingCreate the parking to be added
	 * @return the created entity
	 */
	@PostMapping("/configure")
	public ResponseEntity<ParkingCreate> postParking(@RequestBody final ParkingCreate parkingCreate) {
		final ParkingCreate createdParking;
		try {
			createdParking = parkingService.configureParking(parkingCreate);
		} catch (final Exception e) {
			return null;
		}
		return ResponseEntity.ok(createdParking);
	}
	
	
	/**
	 * 
	 * @param parking the parking to be added
	 * @return the created entity
	 */
	@PutMapping
	@Transactional
	public ResponseEntity<Parking> putParking(@RequestBody final Parking parking) {
		LOGGER.info(String.format("REST request to PUT parking : %s", parking));
		final Parking updatedParking;
		try {
			updatedParking = parkingService.updateParking(parking);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong updating the parking: %s", parking));
			return null;
		}
		return ResponseEntity.ok(updatedParking);
	}

	
	/**
	 * 
	 * @param id the identifier for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/id/{id}")
	@Transactional
	public ResponseEntity<Parking> deleteParking(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to DELETE parking with id : %s", id));
		final Parking deletedParking;
		try {
			deletedParking = parkingService.removeParkingById(id);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the parking with the id: %s", id));
			return null;
		}
		return ResponseEntity.ok(deletedParking);
	}
	
	/**
	 * 
	 * @param code the unique code for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/code/{code}")
	@Transactional
	public ResponseEntity<Parking> deleteParking(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to DELETE parking with code : %s", code));
		final Parking deletedParking;
		try {
			deletedParking = parkingService.removeParkingByCode(code);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the parking with the code: %s", code));
			return null;
		}
		return ResponseEntity.ok(deletedParking);
	}

}
