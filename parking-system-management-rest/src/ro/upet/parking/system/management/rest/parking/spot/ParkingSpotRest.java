package ro.upet.parking.system.management.rest.parking.spot;

import java.util.List;

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

	@Inject
	private ParkingSpotService parkingSpotService;
	
	/**
	 * @param code unique for each parkingSpot
	 * @return the parkingSpot with the corresponding code
	 */
	@GetMapping(path = "/code/{code}")
	public ResponseEntity<ParkingSpot> getParkingSpot(@PathVariable final String code) {
		final ParkingSpot parkingSpot = parkingSpotService.getParkingSpotByCode(code);
		if (parkingSpot == null) {
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
		final ParkingSpot parkingSpot = parkingSpotService.getParkingSpotById(id);
		if (parkingSpot == null) {
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
		final List<ParkingSpot> parkingSpotList= parkingSpotService.getParkingSpotList();
		if (parkingSpotList == null) {
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
		final ParkingSpot createdParkingSpot;
		try {
			createdParkingSpot = parkingSpotService.addParkingSpot(parkingSpot);
		} catch (final Exception e) {
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
		final ParkingSpot updatedParkingSpot;
		try {
			updatedParkingSpot = parkingSpotService.updateParkingSpot(parkingSpot);
		} catch (final Exception e) {
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
		final ParkingSpot deletedParkingSpot;
		try {
			deletedParkingSpot = parkingSpotService.removeParkingSpotById(id);
		} catch (final Exception e) {
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
		final ParkingSpot deletedParkingSpot;
		try {
			deletedParkingSpot = parkingSpotService.removeParkingSpotByCode(code);
		} catch (final Exception e) {
			return null;
		}
		return ResponseEntity.ok(deletedParkingSpot);
	}

}
