package ro.upet.parking.system.management.rest.parking.zone;

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

import ro.upet.parking.system.management.business.api.parking.zone.ParkingZoneService;
import ro.upet.parking.system.management.model.parking.zone.ParkingZone;

/**
 * @author Andrada
 * Rest controller for the parkingZones
 */
@RestController
@RequestMapping(value = "/v1/parkingZones")
@CrossOrigin(maxAge = 3600)
public class ParkingZoneRest {

	@Inject
	private ParkingZoneService parkingZoneService;
	
	/**
	 * @param code unique for each parkingZone
	 * @return the parkingZone with the corresponding code
	 */
	@GetMapping(path = "/code/{code}")
	public ResponseEntity<ParkingZone> getParkingZone(@PathVariable final String code) {
		final ParkingZone parkingZone = parkingZoneService.getParkingZoneByCode(code);
		if (parkingZone == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(parkingZone);
		}
	}
	
	/**
	 * @param id of the parkingZone
	 * @return the parkingZone with the corresponding id
	 */
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<ParkingZone> getParkingZone(@PathVariable final Long id) {
		final ParkingZone parkingZone = parkingZoneService.getParkingZoneById(id);
		if (parkingZone == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(parkingZone);
		}
	}


	/**
	 * @return the list with all the parkingZones
	 */
	@GetMapping
	public ResponseEntity<List<ParkingZone>> getParkingZones() {
		final List<ParkingZone> parkingZoneList= parkingZoneService.getParkingZoneList();
		if (parkingZoneList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(parkingZoneList);
		}
	}

	/**
	 * 
	 * @param parkingZone the parkingZone to be added
	 * @return the created entity
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<ParkingZone> postParkingZone(@RequestBody final ParkingZone parkingZone) {
		final ParkingZone createdParkingZone;
		try {
			createdParkingZone = parkingZoneService.addParkingZone(parkingZone);
		} catch (final Exception e) {
			return null;
		}
		return ResponseEntity.ok(createdParkingZone);
	}
	
	
	/**
	 * 
	 * @param parkingZone the parkingZone to be added
	 * @return the created entity
	 */
	@PutMapping
	@Transactional
	public ResponseEntity<ParkingZone> putParkingZone(@RequestBody final ParkingZone parkingZone) {
		final ParkingZone updatedParkingZone;
		try {
			updatedParkingZone = parkingZoneService.updateParkingZone(parkingZone);
		} catch (final Exception e) {
			return null;
		}
		return ResponseEntity.ok(updatedParkingZone);
	}

	
	/**
	 * 
	 * @param id the identifier for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/id/{id}")
	@Transactional
	public ResponseEntity<ParkingZone> deleteParkingZone(@PathVariable final Long id) {
		final ParkingZone deletedParkingZone;
		try {
			deletedParkingZone = parkingZoneService.removeParkingZoneById(id);
		} catch (final Exception e) {
			return null;
		}
		return ResponseEntity.ok(deletedParkingZone);
	}
	
	/**
	 * 
	 * @param code the unique code for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/code/{code}")
	@Transactional
	public ResponseEntity<ParkingZone> deleteParkingZone(@PathVariable final String code) {
		final ParkingZone deletedParkingZone;
		try {
			deletedParkingZone = parkingZoneService.removeParkingZoneByCode(code);
		} catch (final Exception e) {
			return null;
		}
		return ResponseEntity.ok(deletedParkingZone);
	}

}
