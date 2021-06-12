package ro.upet.parking.system.management.business.impl.payment.options;

import java.util.List;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.business.impl.base.GenericMapper;
import ro.upet.parking.system.management.data.api.payment.options.PaymentOptionsEntity;
import ro.upet.parking.system.management.model.payment.options.MdfPaymentOptions;
import ro.upet.parking.system.management.model.payment.options.PaymentOptions;

/**
 * 
 * @author Andrada
 * Mapper for the paymentOptions entity and model
 */
public class PaymentOptionsMapper {

	private static final GenericMapper<PaymentOptionsEntity, PaymentOptions> MAPPER = new GenericMapper();
	
	/**
	 * @param paymentOptions model for the paymentOptions
	 * @return the corresponding entity
	 */
	public static PaymentOptionsEntity toPaymentOptionsEntity(final PaymentOptions paymentOptions) {
		final PaymentOptionsEntity entity = new PaymentOptionsEntity();
		MAPPER.mapToEntity(paymentOptions, entity);
		return entity;
	}
	
	/**
	 * @param entity database level paymentOptions
	 * @return the model for the entity
	 */
	public static PaymentOptions toPaymentOptions(final PaymentOptionsEntity entity) {
		MdfPaymentOptions model = MdfPaymentOptions.create();
		MAPPER.mapToModel(entity, model);
		model.setUserId(entity.getId());
		return model.toImmutable();
	}
	
	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<PaymentOptions> toPaymentOptionsList (final List<PaymentOptionsEntity> entityList) {
		return entityList.stream().map(PaymentOptionsMapper::toPaymentOptions).collect(Collectors.toList());
	}
}
