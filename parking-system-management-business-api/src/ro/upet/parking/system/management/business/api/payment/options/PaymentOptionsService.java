package ro.upet.parking.system.management.business.api.payment.options;

import java.util.List;

import ro.upet.parking.system.management.model.payment.options.PaymentOptions;

public interface PaymentOptionsService {
	/**
	 * @param paymentOptionsId the id of the paymentOptions searched
	 * @return the requested paymentOptions
	 */
	public PaymentOptions getPaymentOptionsById(final Long paymentOptionsId);
	
	/**
	 * @param paymentOptionsCode the code of the paymentOptions searched 
	 * @return the requested paymentOptions
	 */
	public PaymentOptions getPaymentOptionsByCode(final String paymentOptionsCode);
	
	/**
	 * @return the list of all the paymentOptionss
	 */
	public List<PaymentOptions> getPaymentOptionsList();
	
	/**
	 * @param paymentOptions the entity to be added
	 * @return the added entity
	 */
	public PaymentOptions addPaymentOptions(final PaymentOptions paymentOptions);
	
	/**
	 * @param paymentOptions the updated paymentOptions
	 * @return the updated entity
	 */
	public PaymentOptions updatePaymentOptions(final PaymentOptions paymentOptions);
	
	/**
	 * @param paymentOptionsId the id of the entity that will be deleted
	 * @return the deleted entity
	 * @throws Exception 
	 */
	public PaymentOptions removePaymentOptionsById(final Long paymentOptionsId) throws Exception;
	
	/**
	 * @param paymentOptionsCode the code of the entity that will be deleted
	 * @return the deleted entity
	 */
	public PaymentOptions removePaymentOptionsByCode(final String paymentOptionsCode);
}
