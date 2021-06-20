package ro.upet.parking.system.management.rest.parking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.business.api.parking.ParkingService;
import ro.upet.parking.system.management.model.parking.Parking;
import ro.upet.parking.system.management.model.parking.ParkingCreate;
import ro.upet.parking.system.management.rest.base.BaseController;

import javax.inject.Inject;

/**
 * @author Andrada
 * Rest controller for the parkings
 */
@RestController
@RequestMapping(value = "/v1/parkings")
@CrossOrigin(maxAge = 3600)
public class ParkingController extends BaseController<Parking> {

	private static final String CONFIGURE_PATH = "/configure";

	@Inject
	private ParkingService service;
	
	
	@Override
	@Inject
	public void setService(BaseService<Parking> service) {
		super.setService(this.service);
	}

	
	/**
	 * 
	 * @param parkingCreate the parking to be added
	 * @return the created entity
	 */
	@PostMapping(CONFIGURE_PATH)
	public ResponseEntity<ParkingCreate> postParking(@RequestBody final ParkingCreate parkingCreate) {
		final ParkingCreate createdParking;
		try {
			createdParking = service.configureParking(parkingCreate);
		} catch (final Exception e) {
			return null;
		}
		return ResponseEntity.ok(createdParking);
	}
	

}
