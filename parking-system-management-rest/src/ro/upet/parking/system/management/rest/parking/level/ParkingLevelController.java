package ro.upet.parking.system.management.rest.parking.level;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.business.api.parking.level.ParkingLevelService;
import ro.upet.parking.system.management.model.parking.level.ParkingLevel;
import ro.upet.parking.system.management.rest.base.BaseController;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Andrada
 * Rest controller for the parkingLevels
 */
@RestController
@RequestMapping(value = "/v1/parkingLevels")
@CrossOrigin(maxAge = 3600)
@Slf4j
public class ParkingLevelController extends BaseController<ParkingLevel> {


	private static final String PARKING_PARKING_ID_PATH = "/parking/{parkingId}";

	@Inject
	private ParkingLevelService service;
	
	@Override
	@Inject
	public void setService(BaseService<ParkingLevel> service) {
		super.setService(this.service);
	}
	
	
	/**
	 * @param parkingId the id of the parking 
	 * @return the list of all the levels for the parking 
	 */
	@GetMapping(PARKING_PARKING_ID_PATH)
	public ResponseEntity<List<ParkingLevel>> getParkingZonesByParking(@PathVariable final Long parkingId) {
		log.info("REST request to GET parkingLevel by parkingId :{}", parkingId);
		final List<ParkingLevel> parkingLevelList= service.getParkingLevelListByParking(parkingId);
		if (parkingLevelList == null) {
			log.info("No parkingLevels found for the parking with id: {}", parkingId);
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(parkingLevelList);
		}
	}

}
