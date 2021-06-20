package ro.upet.parking.system.management.rest.payment.options;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.business.api.payment.options.PaymentOptionsService;
import ro.upet.parking.system.management.model.payment.options.PaymentOptions;
import ro.upet.parking.system.management.rest.base.BaseController;

import javax.inject.Inject;

/**
 * @author Andrada
 * Rest controller for the paymentOptionss
 */
@RestController
@RequestMapping(value = "/v1/paymentOptionss")
@CrossOrigin(maxAge = 3600)
public class PaymentOptionsController extends BaseController<PaymentOptions> {

	@Inject
	private PaymentOptionsService service;
	
	@Override
	@Inject
	public void setService(BaseService<PaymentOptions> service) {
		super.setService(this.service);
	}


}
