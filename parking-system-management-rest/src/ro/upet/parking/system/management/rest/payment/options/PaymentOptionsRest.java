package ro.upet.parking.system.management.rest.payment.options;

import java.util.List;
import java.util.logging.Logger;

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

import ro.upet.parking.system.management.business.api.payment.options.PaymentOptionsService;
import ro.upet.parking.system.management.model.payment.options.PaymentOptions;

/**
 * @author Andrada
 * Rest controller for the paymentOptionss
 */
@RestController
@RequestMapping(value = "/v1/paymentOptionss")
@CrossOrigin(maxAge = 3600)
public class PaymentOptionsRest {

	private static final Logger LOGGER  = Logger.getLogger(PaymentOptionsRest.class.getName());
	
	@Inject
	private PaymentOptionsService paymentOptionsService;
	
	/**
	 * @param code unique for each paymentOptions
	 * @return the paymentOptions with the corresponding code
	 */
	@GetMapping(path = "/code/{code}")
	public ResponseEntity<PaymentOptions> getPaymentOptions(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to GET paymentOptions by code: %s", code));
		final PaymentOptions paymentOptions = paymentOptionsService.getPaymentOptionsByCode(code);
		if (paymentOptions == null) {
			LOGGER.info(String.format("PaymentOptions with code: %s does not exist", code));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(paymentOptions);
		}
	}
	
	/**
	 * @param id of the paymentOptions
	 * @return the paymentOptions with the corresponding id
	 */
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<PaymentOptions> getPaymentOptions(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to GET paymentOptions by id: %s", id));
		final PaymentOptions paymentOptions = paymentOptionsService.getPaymentOptionsById(id);
		if (paymentOptions == null) {
			LOGGER.info(String.format("PaymentOptions with id: %s does not exist", id));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(paymentOptions);
		}
	}


	/**
	 * @return the list with all the paymentOptionss
	 */
	@GetMapping
	public ResponseEntity<List<PaymentOptions>> getPaymentOptionss() {
		LOGGER.info(String.format("REST request to GET all paymentOptionss"));
		final List<PaymentOptions> paymentOptionsList= paymentOptionsService.getPaymentOptionsList();
		if (paymentOptionsList == null) {
			LOGGER.info(String.format("No paymentOptionss found"));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(paymentOptionsList);
		}
	}

	/**
	 * 
	 * @param paymentOptions the paymentOptions to be added
	 * @return the created entity
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<PaymentOptions> postPaymentOptions(@RequestBody final PaymentOptions paymentOptions) {
		LOGGER.info(String.format("REST request to POST paymentOptions : %s", paymentOptions));
		final PaymentOptions createdPaymentOptions;
		try {
			createdPaymentOptions = paymentOptionsService.addPaymentOptions(paymentOptions);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong creating the paymentOptions : %s", paymentOptions));
			return null;
		}
		return ResponseEntity.ok(createdPaymentOptions);
	}
	
	
	/**
	 * 
	 * @param paymentOptions the paymentOptions to be added
	 * @return the created entity
	 */
	@PutMapping
	@Transactional
	public ResponseEntity<PaymentOptions> putPaymentOptions(@RequestBody final PaymentOptions paymentOptions) {
		LOGGER.info(String.format("REST request to PUT paymentOptions : %s", paymentOptions));
		final PaymentOptions updatedPaymentOptions;
		try {
			updatedPaymentOptions = paymentOptionsService.updatePaymentOptions(paymentOptions);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong updating the paymentOptions: %s", paymentOptions));
			return null;
		}
		return ResponseEntity.ok(updatedPaymentOptions);
	}

	
	/**
	 * 
	 * @param id the identifier for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/id/{id}")
	@Transactional
	public ResponseEntity<PaymentOptions> deletePaymentOptions(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to DELETE paymentOptions with id : %s", id));
		final PaymentOptions deletedPaymentOptions;
		try {
			deletedPaymentOptions = paymentOptionsService.removePaymentOptionsById(id);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the paymentOptions with the id: %s", id));
			return null;
		}
		return ResponseEntity.ok(deletedPaymentOptions);
	}
	
	/**
	 * 
	 * @param code the unique code for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/code/{code}")
	@Transactional
	public ResponseEntity<PaymentOptions> deletePaymentOptions(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to DELETE paymentOptions with code : %s", code));
		final PaymentOptions deletedPaymentOptions;
		try {
			deletedPaymentOptions = paymentOptionsService.removePaymentOptionsByCode(code);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the paymentOptions with the code: %s", code));
			return null;
		}
		return ResponseEntity.ok(deletedPaymentOptions);
	}

}
