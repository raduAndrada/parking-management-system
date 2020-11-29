package ro.upet.parking.system.management.business.impl.payment.options;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ro.upet.parking.system.management.business.api.payment.options.PaymentOptionsService;
import ro.upet.parking.system.management.data.api.payment.options.PaymentOptionsEntity;
import ro.upet.parking.system.management.data.impl.payment.options.PaymentOptionsRepository;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.model.payment.options.PaymentOptions;

/**
 * @author Andrada
 * Business level logic implementation for paymentOptionss 
 */
@Service
public class PaymentOptionsServiceImpl implements PaymentOptionsService{
	
	@Inject
	PaymentOptionsRepository paymentOptionsRepo;
	
	@Inject
	UserRepository userRepo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PaymentOptions getPaymentOptionsById(final Long paymentOptionsId) {
		return PaymentOptionsMapper.toPaymentOptions(paymentOptionsRepo.getOne(paymentOptionsId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PaymentOptions getPaymentOptionsByCode(final String paymentOptionsCode) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PaymentOptions> getPaymentOptionsList() {
		return PaymentOptionsMapper.toPaymentOptionsList(paymentOptionsRepo.findAll());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public PaymentOptions addPaymentOptions(final PaymentOptions paymentOptions) {
		final PaymentOptionsEntity entity = PaymentOptionsMapper.toPaymentOptionsEntity(paymentOptions);
		entity.setUser(userRepo.getOne(paymentOptions.getUserId()));
		entity.setCreatedAt(Instant.now());
		entity.setUpdatedAt(Instant.now());
		final PaymentOptionsEntity savedEntity = paymentOptionsRepo.save(entity);
		return PaymentOptionsMapper.toPaymentOptions(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public PaymentOptions updatePaymentOptions(final PaymentOptions paymentOptions) {
		final PaymentOptionsEntity entity = PaymentOptionsMapper.toPaymentOptionsEntity(paymentOptions);
		entity.setUser(userRepo.getOne(paymentOptions.getUserId()));
		entity.setUpdatedAt(Instant.now());
		final PaymentOptionsEntity savedEntity = paymentOptionsRepo.save(entity);
		return PaymentOptionsMapper.toPaymentOptions(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public PaymentOptions removePaymentOptionsById(final Long paymentOptionsId) throws Exception {
		final PaymentOptionsEntity entity = paymentOptionsRepo.getOne(paymentOptionsId);
		if (entity == null ) {
			throw new Exception();
		}
		paymentOptionsRepo.deleteById(paymentOptionsId);
		return PaymentOptionsMapper.toPaymentOptions(entity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public PaymentOptions removePaymentOptionsByCode(final String paymentOptionsCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
