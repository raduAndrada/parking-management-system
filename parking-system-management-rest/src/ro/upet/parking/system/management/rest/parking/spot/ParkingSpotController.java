package ro.upet.parking.system.management.rest.parking.spot;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.business.api.parking.spot.ParkingSpotService;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;
import ro.upet.parking.system.management.rest.base.BaseController;

import javax.inject.Inject;

/**
 * @author Andrada
 * Rest controller for the parkingSpots
 */
@RestController
@RequestMapping(value = "/v1/parkingSpots")
@CrossOrigin(maxAge = 3600)
public class ParkingSpotController extends BaseController<ParkingSpot> {

	@Inject
	private ParkingSpotService service;
	
	@Override
	@Inject
	public void setService(BaseService<ParkingSpot> service) {
		super.setService(this.service);
	}
	

}
