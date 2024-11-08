package ro.upet.parking.system.management.rest.parking.zone;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.business.api.parking.zone.ParkingZoneService;
import ro.upet.parking.system.management.model.parking.zone.ParkingZone;
import ro.upet.parking.system.management.rest.base.BaseController;

import javax.inject.Inject;

/**
 * @author Andrada
 * Rest controller for the parkingZones
 */
@RestController
@RequestMapping(value = "/v1/parkingZones")
@CrossOrigin(maxAge = 3600)
public class ParkingZoneController extends BaseController<ParkingZone> {

	@Inject
	private ParkingZoneService service;
	
	@Override
	@Inject
	public void setService(BaseService<ParkingZone> service) {
		super.setService(this.service);
	}

}
