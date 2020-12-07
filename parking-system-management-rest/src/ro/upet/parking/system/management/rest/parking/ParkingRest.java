package ro.upet.parking.system.management.rest.parking;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.business.api.parking.ParkingService;
import ro.upet.parking.system.management.model.parking.Parking;
import ro.upet.parking.system.management.model.parking.ParkingCreate;
import ro.upet.parking.system.management.rest.base.BaseRest;

/**
 * @author Andrada
 * Rest controller for the parkings
 */
@RestController
@RequestMapping(value = "/v1/parkings")
@CrossOrigin(maxAge = 3600)
public class ParkingRest  extends BaseRest<Parking>{

	@Inject
	private ParkingService service;
	
	
	@Override
	public void setService(BaseService<Parking> service) {
		super.setService(this.service);
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
			createdParking = service.configureParking(parkingCreate);
		} catch (final Exception e) {
			return null;
		}
		return ResponseEntity.ok(createdParking);
	}
	

}
