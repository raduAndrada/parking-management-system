package ro.upet.parking.system.management.business.impl.payment.options;

import java.util.List;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.data.api.payment.options.PaymentOptionsEntity;
import ro.upet.parking.system.management.model.payment.options.ImtPaymentOptions;
import ro.upet.parking.system.management.model.payment.options.PaymentOptions;

/**
 * 
 * @author Andrada
 * Mapper for the paymentOptions entity and model
 */
public class PaymentOptionsMapper {

	/**
	 * @param paymentOptions model for the paymentOptions
	 * @return the corresponding entity
	 */
	public static PaymentOptionsEntity toPaymentOptionsEntity(final PaymentOptions paymentOptions) {
		final PaymentOptionsEntity entity = new PaymentOptionsEntity();
		entity.setCode(paymentOptions.getCode());
		entity.setId(paymentOptions.getId());
		entity.setCreatedAt(paymentOptions.getCreatedAt());
		entity.setUpdatedAt(paymentOptions.getUpdatedAt());
		entity.setPaymentStatus(paymentOptions.getPaymentStatus());
		entity.setStartPeriod(paymentOptions.getStartPeriod());
		entity.setEndPeriod(paymentOptions.getEndPeriod());
		return entity;
	}
	
	/**
	 * @param entity database level paymentOptions
	 * @return the model for the entity
	 */
	public static PaymentOptions toPaymentOptions(final PaymentOptionsEntity entity) {
		return ImtPaymentOptions.builder()
				.code(entity.getCode())
				.createdAt(entity.getCreatedAt())
				.id(entity.getId())
				.updatedAt(entity.getUpdatedAt())
				.userCode(entity.getUser().getCode())
				.userId(entity.getUser().getId())
				.endPeriod(entity.getEndPeriod())
				.startPeriod(entity.getStartPeriod())
				.paymentStatus(entity.getPaymentStatus())
				.build();
	}
	
	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<PaymentOptions> toPaymentOptionsList (final List<PaymentOptionsEntity> entityList) {
		return entityList.stream().map(PaymentOptionsMapper::toPaymentOptions).collect(Collectors.toList());
	}
}
