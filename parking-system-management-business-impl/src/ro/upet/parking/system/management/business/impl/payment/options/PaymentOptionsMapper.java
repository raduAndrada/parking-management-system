package ro.upet.parking.system.management.business.impl.payment.options;

import org.modelmapper.ModelMapper;
import ro.upet.parking.system.management.data.api.payment.options.PaymentOptionsEntity;
import ro.upet.parking.system.management.model.payment.options.PaymentOptions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author Andrada
 * Mapper for the paymentOptions entity and model
 */
public class PaymentOptionsMapper {

	private static final ModelMapper MAPPER = new ModelMapper();

	
	/**
	 * @param paymentOptions model for the paymentOptions
	 * @return the corresponding entity
	 */
	public static PaymentOptionsEntity toPaymentOptionsEntity(final PaymentOptions paymentOptions) {
		return MAPPER.map(paymentOptions, PaymentOptionsEntity.class);
	}
	
	/**
	 * @param entity database level paymentOptions
	 * @return the model for the entity
	 */
	public static PaymentOptions toPaymentOptions(final PaymentOptionsEntity entity) {
		PaymentOptions model =MAPPER.map(entity, PaymentOptions.class);
		MAPPER.map(entity, model);
		model.setUserId(entity.getId());
		return model;
	}
	
	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<PaymentOptions> toPaymentOptionsList (final List<PaymentOptionsEntity> entityList) {
		return entityList.stream().map(PaymentOptionsMapper::toPaymentOptions).collect(Collectors.toList());
	}
}
