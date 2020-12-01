package ro.upet.parking.system.management.rest.parking.spot;

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

import ro.upet.parking.system.management.business.api.parking.spot.ParkingSpotService;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;

/**
 * @author Andrada
 * Rest controller for the parkingSpots
 */
@RestController
@RequestMapping(value = "/v1/parkingSpots")
@CrossOrigin(maxAge = 3600)
public class ParkingSpotRest {

	private static final Logger LOGGER  = Logger.getLogger(ParkingSpotRest.class.getName());
	
	@Inject
	private ParkingSpotService parkingSpotService;
	
	/**
	 * @param code unique for each parkingSpot
	 * @return the parkingSpot with the corresponding code
	 */
	@GetMapping(path = "/code/{code}")
	public ResponseEntity<ParkingSpot> getParkingSpot(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to GET parkingSpot by code: %s", code));
		final ParkingSpot parkingSpot = parkingSpotService.getParkingSpotByCode(code);
		if (parkingSpot == null) {
			LOGGER.info(String.format("ParkingSpot with code: %s does not exist", code));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(parkingSpot);
		}
	}
	
	/**
	 * @param id of the parkingSpot
	 * @return the parkingSpot with the corresponding id
	 */
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<ParkingSpot> getParkingSpot(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to GET parkingSpot by id: %s", id));
		final ParkingSpot parkingSpot = parkingSpotService.getParkingSpotById(id);
		if (parkingSpot == null) {
			LOGGER.info(String.format("ParkingSpot with id: %s does not exist", id));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(parkingSpot);
		}
	}


	/**
	 * @return the list with all the parkingSpots
	 */
	@GetMapping
	public ResponseEntity<List<ParkingSpot>> getParkingSpots() {
		LOGGER.info(String.format("REST request to GET all parkingSpots"));
		final List<ParkingSpot> parkingSpotList= parkingSpotService.getParkingSpotList();
		if (parkingSpotList == null) {
			LOGGER.info(String.format("No parkingSpots found"));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(parkingSpotList);
		}
	}


	/**
	 * 
	 * @param parkingSpot the parkingSpot to be added
	 * @return the created entity
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<ParkingSpot> postParkingSpot(@RequestBody final ParkingSpot parkingSpot) {
		LOGGER.info(String.format("REST request to POST parkingSpot : %s", parkingSpot));
		final ParkingSpot createdParkingSpot;
		try {
			createdParkingSpot = parkingSpotService.addParkingSpot(parkingSpot);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong creating the parkingSpot : %s", parkingSpot));
			return null;
		}
		return ResponseEntity.ok(createdParkingSpot);
	}
	
	
	/**
	 * 
	 * @param parkingSpot the parkingSpot to be added
	 * @return the created entity
	 */
	@PutMapping
	@Transactional
	public ResponseEntity<ParkingSpot> putParkingSpot(@RequestBody final ParkingSpot parkingSpot) {
		LOGGER.info(String.format("REST request to PUT parkingSpot : %s", parkingSpot));
		final ParkingSpot updatedParkingSpot;
		try {
			updatedParkingSpot = parkingSpotService.updateParkingSpot(parkingSpot);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong updating the parkingSpot: %s", parkingSpot));
			return null;
		}
		return ResponseEntity.ok(updatedParkingSpot);
	}

	
	/**
	 * 
	 * @param id the identifier for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/id/{id}")
	@Transactional
	public ResponseEntity<ParkingSpot> deleteParkingSpot(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to DELETE parkingSpot with id : %s", id));
		final ParkingSpot deletedParkingSpot;
		try {
			deletedParkingSpot = parkingSpotService.removeParkingSpotById(id);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the parkingSpot with the id: %s", id));
			return null;
		}
		return ResponseEntity.ok(deletedParkingSpot);
	}
	
	/**
	 * 
	 * @param code the unique code for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/code/{code}")
	@Transactional
	public ResponseEntity<ParkingSpot> deleteParkingSpot(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to DELETE parkingSpot with code : %s", code));
		final ParkingSpot deletedParkingSpot;
		try {
			deletedParkingSpot = parkingSpotService.removeParkingSpotByCode(code);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the parkingSpot with the code: %s", code));
			return null;
		}
		return ResponseEntity.ok(deletedParkingSpot);
	}

}
