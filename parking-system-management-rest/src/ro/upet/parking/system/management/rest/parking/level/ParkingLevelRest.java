package ro.upet.parking.system.management.rest.parking.level;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.business.api.parking.level.ParkingLevelService;
import ro.upet.parking.system.management.model.parking.level.ParkingLevel;
import ro.upet.parking.system.management.rest.base.BaseRest;

/**
 * @author Andrada
 * Rest controller for the parkingLevels
 */
@RestController
@RequestMapping(value = "/v1/parkingLevels")
@CrossOrigin(maxAge = 3600)
public class ParkingLevelRest extends BaseRest<ParkingLevel>{

	@Inject
	private ParkingLevelService service;
	
	
	@Override
	public void setService(BaseService<ParkingLevel> service) {
		super.setService(this.service);
	}
	
	
	/**
	 * @param parkingId the id of the parking 
	 * @return the list of all the levels for the parking 
	 */
	@GetMapping("/parking/{parkingId}")
	public ResponseEntity<List<ParkingLevel>> getParkingZonesByParking(@PathVariable final Long parkingId) {
		LOGGER.info(String.format("REST request to GET parkingLevel by parkingId : %s", parkingId));
		final List<ParkingLevel> parkingLevelList= service.getParkingLevelListByParking(parkingId);
		if (parkingLevelList == null) {
			LOGGER.info(String.format("No parkingLevels found for the parking with id: %s", parkingId));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(parkingLevelList);
		}
	}

}
