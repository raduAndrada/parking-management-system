package ro.upet.parking.system.management.data.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ro.upet.parking.system.management.data.DataTests;
import ro.upet.parking.system.management.data.api.payment.options.PaymentOptionsEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.impl.payment.options.PaymentOptionsRepository;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.model.base.PaymentStatus;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentOptionsRepositoryIT extends DataTests {
	
		private static final PaymentStatus PAYMENT_OPTIONS_STATUS1 = PaymentStatus.NOT_PAID;
	    private static final PaymentStatus  PAYMENT_OPTIONS_STATUS2 = PaymentStatus.PAID;
	    
	    private static final LocalDate PAYMENT_OPTIONS_START_PERIOD1 = LocalDate.parse("2020-12-01");
	    private static final LocalDate PAYMENT_OPTIONS_START_PERIOD2 = LocalDate.parse("2020-11-30");
	    
	    private static final LocalDate PAYMENT_OPTIONS_END_PERIOD1 = LocalDate.parse("2021-12-01");
	    private static final LocalDate PAYMENT_OPTIONS_END_PERIOD2 = LocalDate.parse("2021-01-01");
	    
	    private static final PaymentOptionsEntity PAYMENT_OPTIONS_1 = new PaymentOptionsEntity();
	    private static final PaymentOptionsEntity PAYMENT_OPTIONS_2 = new PaymentOptionsEntity();

	    @Inject
	    private PaymentOptionsRepository paymentOptionsRepo;
	    
	    @Inject
	    private UserRepository userRepo;

	    @Before
	    public void init() {
	    	PAYMENT_OPTIONS_1.setStartPeriod(PAYMENT_OPTIONS_START_PERIOD1);
	    	PAYMENT_OPTIONS_1.setEndPeriod(PAYMENT_OPTIONS_END_PERIOD1);
	    	PAYMENT_OPTIONS_1.setPaymentStatus(PAYMENT_OPTIONS_STATUS1);
	    	final UserEntity temp1 = new UserEntity();
	    	userRepo.save(temp1);	
	    	PAYMENT_OPTIONS_1.setUser(temp1);

	    	PAYMENT_OPTIONS_2.setStartPeriod(PAYMENT_OPTIONS_START_PERIOD2);
	    	PAYMENT_OPTIONS_2.setEndPeriod(PAYMENT_OPTIONS_END_PERIOD2);
	    	PAYMENT_OPTIONS_2.setPaymentStatus(PAYMENT_OPTIONS_STATUS2);
	    	final UserEntity temp2 = new UserEntity();
	    	userRepo.save(temp2);	
	    	PAYMENT_OPTIONS_1.setUser(temp2);
	    }
	    
	    @Test
	    @Transactional
	    public void addPaymentOptions_test_success() {
	        final PaymentOptionsEntity testEntity = paymentOptionsRepo.save(PAYMENT_OPTIONS_1);
	        final Optional<PaymentOptionsEntity> expectedEntity = paymentOptionsRepo.findById(testEntity.getId());
	        assertThat(expectedEntity).isNotNull();
	        assertThat(expectedEntity.get().getPaymentStatus()).isEqualTo(PAYMENT_OPTIONS_STATUS1);
	    }
	    
	    @Test
	    @Transactional
	    public void deletePaymentOptions_test_success() {
	        final PaymentOptionsEntity testEntity = paymentOptionsRepo.save(PAYMENT_OPTIONS_1);
	        paymentOptionsRepo.delete(testEntity);
	        final Optional<PaymentOptionsEntity> expectedEntity = paymentOptionsRepo.findById(testEntity.getId());
	        assertThat(expectedEntity.isEmpty());
	    }
	    
	
	    
	    @Test
	    @Transactional
	    public void findAllPaymentOptionss_test_success() {
	        paymentOptionsRepo.save(PAYMENT_OPTIONS_1);
	        paymentOptionsRepo.save(PAYMENT_OPTIONS_2);
	        final List<PaymentOptionsEntity> expectedList = paymentOptionsRepo.findAll();
	        assertThat(expectedList).isNotNull();
	        assertThat(expectedList.size()).isEqualTo(2);
	    }
	    
	    
	    @Test
	    @Transactional
	    public void updatePaymentOptions_test_success() {
	        final PaymentOptionsEntity testEntity = paymentOptionsRepo.save(PAYMENT_OPTIONS_1);
	        testEntity.setPaymentStatus(PAYMENT_OPTIONS_STATUS2);
	        paymentOptionsRepo.save(testEntity);
	        final Optional<PaymentOptionsEntity> expectedEntity = paymentOptionsRepo.findById(testEntity.getId());
	        assertThat(expectedEntity).isNotNull();
	        assertThat(expectedEntity.get().getPaymentStatus()).isEqualTo(PAYMENT_OPTIONS_STATUS2);
	    }
}

