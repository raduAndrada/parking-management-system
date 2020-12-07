package ro.upet.parking.system.management.rest.parking.spot;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.business.api.parking.spot.ParkingSpotService;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;
import ro.upet.parking.system.management.rest.base.BaseRest;

/**
 * @author Andrada
 * Rest controller for the parkingSpots
 */
@RestController
@RequestMapping(value = "/v1/parkingSpots")
@CrossOrigin(maxAge = 3600)
public class ParkingSpotRest extends BaseRest<ParkingSpot>{

	@Inject
	private ParkingSpotService service;
	
	@Override
	public void setService(BaseService<ParkingSpot> service) {
		super.setService(this.service);
	}
	

}
