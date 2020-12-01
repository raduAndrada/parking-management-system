package ro.upet.parking.system.management.rest.parking.level;

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

import ro.upet.parking.system.management.business.api.parking.level.ParkingLevelService;
import ro.upet.parking.system.management.model.parking.level.ParkingLevel;

/**
 * @author Andrada
 * Rest controller for the parkingLevels
 */
@RestController
@RequestMapping(value = "/v1/parkingLevels")
@CrossOrigin(maxAge = 3600)
public class ParkingLevelRest {
	
	private static final Logger LOGGER  = Logger.getLogger(ParkingLevelRest.class.getName());

	@Inject
	private ParkingLevelService parkingLevelService;
	
	/**
	 * @param code unique for each parkingLevel
	 * @return the parkingLevel with the corresponding code
	 */
	@GetMapping(path = "/code/{code}")
	public ResponseEntity<ParkingLevel> getParkingLevel(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to GET parkingLevel by code: %s", code));
		final ParkingLevel parkingLevel = parkingLevelService.getParkingLevelByCode(code);
		if (parkingLevel == null) {
			LOGGER.info(String.format("ParkingLevel with code: %s does not exist", code));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(parkingLevel);
		}
	}
	
	/**
	 * @param id of the parkingLevel
	 * @return the parkingLevel with the corresponding id
	 */
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<ParkingLevel> getParkingLevel(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to GET parkingLevel by id: %s", id));
		final ParkingLevel parkingLevel = parkingLevelService.getParkingLevelById(id);
		if (parkingLevel == null) {
			LOGGER.info(String.format("ParkingLevel with id: %s does not exist", id));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(parkingLevel);
		}
	}


	/**
	 * @return the list with all the parkingLevels
	 */
	@GetMapping
	public ResponseEntity<List<ParkingLevel>> getParkingLevels() {
		LOGGER.info(String.format("REST request to GET all parkingLevels"));
		final List<ParkingLevel> parkingLevelList= parkingLevelService.getParkingLevelList();
		if (parkingLevelList == null) {
			LOGGER.info(String.format("No parkingLevels found"));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(parkingLevelList);
		}
	}
	
	/**
	 * @param parkingId the id of the parking 
	 * @return the list of all the levels for the parking 
	 */
	@GetMapping("/parking/{parkingId}")
	public ResponseEntity<List<ParkingLevel>> getParkingZonesByParking(@PathVariable final Long parkingId) {
		LOGGER.info(String.format("REST request to GET parkingLevel by parkingId : %s", parkingId));
		final List<ParkingLevel> parkingLevelList= parkingLevelService.getParkingLevelListByParking(parkingId);
		if (parkingLevelList == null) {
			LOGGER.info(String.format("No parkingLevels found for the parking with id: %s", parkingId));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(parkingLevelList);
		}
	}

	/**
	 * 
	 * @param parkingLevel the parkingLevel to be added
	 * @return the created entity
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<ParkingLevel> postParkingLevel(@RequestBody final ParkingLevel parkingLevel) {
		LOGGER.info(String.format("REST request to POST parkingLevel : %s", parkingLevel));
		final ParkingLevel createdParkingLevel;
		try {
			createdParkingLevel = parkingLevelService.addParkingLevel(parkingLevel);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong creating the parkingLevel : %s", parkingLevel));
			return null;
		}
		return ResponseEntity.ok(createdParkingLevel);
	}
	
	
	/**
	 * 
	 * @param parkingLevel the parkingLevel to be added
	 * @return the created entity
	 */
	@PutMapping
	@Transactional
	public ResponseEntity<ParkingLevel> putParkingLevel(@RequestBody final ParkingLevel parkingLevel) {
		LOGGER.info(String.format("REST request to PUT parkingLevel : %s", parkingLevel));
		final ParkingLevel updatedParkingLevel;
		try {
			updatedParkingLevel = parkingLevelService.updateParkingLevel(parkingLevel);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong updating the parkingLevel: %s", parkingLevel));
			return null;
		}
		return ResponseEntity.ok(updatedParkingLevel);
	}

	
	/**
	 * 
	 * @param id the identifier for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/id/{id}")
	@Transactional
	public ResponseEntity<ParkingLevel> deleteParkingLevel(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to DELETE parkingLevel with id : %s", id));
		final ParkingLevel deletedParkingLevel;
		try {
			deletedParkingLevel = parkingLevelService.removeParkingLevelById(id);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the parkingLevel with the id: %s", id));
			return null;
		}
		return ResponseEntity.ok(deletedParkingLevel);
	}
	
	/**
	 * 
	 * @param code the unique code for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/code/{code}")
	@Transactional
	public ResponseEntity<ParkingLevel> deleteParkingLevel(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to DELETE parkingLevel with code : %s", code));
		final ParkingLevel deletedParkingLevel;
		try {
			deletedParkingLevel = parkingLevelService.removeParkingLevelByCode(code);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the parkingLevel with the code: %s", code));
			return null;
		}
		return ResponseEntity.ok(deletedParkingLevel);
	}

}
