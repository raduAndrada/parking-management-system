package ro.upet.parking.system.management.business.impl;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentOptionsServiceIT {

//	@Inject
//	protected PaymentOptionsRepository paymentOptionsRepo;
//
//	@Inject
//	protected PaymentOptionsService paymentOptionsService;
//
//	@Inject
//	private UserRepository userRepo;
//
//	private static final PaymentOptions PAYMENT_OPTIONS_1 = ImtPaymentOptions.builder()
//			.paymentStatus(PaymentStatus.NOT_PAID).startPeriod(LocalDate.parse("2020-10-01"))
//			.endPeriod(LocalDate.parse("2021-02-01")).build();
//
//	@Test
//	@Transactional
//	public void addPaymentOptions_test_success() {
//		UserEntity ue = new UserEntity();
//		ue.setUsername("Andrada");
//		ue.setEmail("Andrada");
//		ue = userRepo.save(ue);
//
//		final PaymentOptions actualResult = paymentOptionsService
//				.add(ImtPaymentOptions.copyOf(PAYMENT_OPTIONS_1).withUserId(ue.getId()));
//		final PaymentOptions expectedResult = ImtPaymentOptions.copyOf(PAYMENT_OPTIONS_1)
//				.withCode(actualResult.getCode()).withUserId(actualResult.getUserId()).withId(actualResult.getId())
//				.withCreatedAt(actualResult.getCreatedAt()).withUpdatedAt(actualResult.getUpdatedAt());
//		assertThat(actualResult).isNotNull();
//		assertThat(actualResult).isEqualTo(expectedResult);
//	}
//
//	@Test
//	@Transactional
//	public void deletePaymentOptionsById_test_success() {
//		PaymentOptionsEntity poe = new PaymentOptionsEntity();
//		UserEntity ue = new UserEntity();
//		ue.setUsername("Andrada");
//		ue = userRepo.save(ue);
//
//		poe.setUser(ue);
//		poe.setPaymentStatus(PaymentStatus.PAID);
//		poe = paymentOptionsRepo.save(poe);
//
//		final List<PaymentOptions> actualResult = paymentOptionsService.getList();
//		paymentOptionsService.removeById(poe.getId());
//		final List<PaymentOptions> expectedResult = paymentOptionsService.getList();
//		assertThat(actualResult).isNotNull();
//		assertThat(actualResult.size() - 1).isEqualTo(expectedResult.size());
//	}
//
//	@Test
//	@Transactional
//	public void findPaymentOptionsById_test_success() {
//		PaymentOptionsEntity poe = new PaymentOptionsEntity();
//		poe.setPaymentStatus(PaymentStatus.PAID);
//		UserEntity ue = new UserEntity();
//		ue = userRepo.save(ue);
//		poe.setUser(ue);
//		poe = paymentOptionsRepo.save(poe);
//		final PaymentOptions actualResult = paymentOptionsService.getById(poe.getId());
//		final PaymentOptions expectedResult = ImtPaymentOptions.builder().id(actualResult.getId())
//				.createdAt(actualResult.getCreatedAt()).updatedAt(actualResult.getUpdatedAt()).userId(ue.getId())
//				.paymentStatus(PaymentStatus.PAID).build();
//		assertThat(actualResult).isNotNull();
//		assertThat(actualResult).isEqualTo(expectedResult);
//	}
//
//	@Test
//	@Transactional
//	public void findAllPaymentOptionss_test_success() {
//		PaymentOptionsEntity poe = new PaymentOptionsEntity();
//		poe.setPaymentStatus(PaymentStatus.PAID);
//		UserEntity ue = new UserEntity();
//		ue = userRepo.save(ue);
//		poe.setUser(ue);
//		paymentOptionsRepo.save(poe);
//
//		poe = new PaymentOptionsEntity();
//		poe.setPaymentStatus(PaymentStatus.NOT_PAID);
//		poe.setUser(ue);
//		paymentOptionsRepo.save(poe);
//
//		final List<PaymentOptions> actualResult = paymentOptionsService.getList();
//		assertThat(actualResult).isNotNull();
//		assertThat(actualResult.size()).isEqualTo(2);
//	}
//
//	@Test
//	@Transactional
//	public void updatePaymentOptions_test_success() {
//		PaymentOptionsEntity poe = new PaymentOptionsEntity();
//		UserEntity ue = new UserEntity();
//		userRepo.save(ue);
//		poe.setUser(ue);
//		poe.setPaymentStatus(PaymentStatus.PREPAID);
//		poe = paymentOptionsRepo.save(poe);
//		final PaymentOptions actualResult = paymentOptionsService
//				.update(ImtPaymentOptions.copyOf(PAYMENT_OPTIONS_1).withId(poe.getId()).withUserId(ue.getId()));
//		final PaymentOptions expectedResult = ImtPaymentOptions.copyOf(PAYMENT_OPTIONS_1).withUserId(ue.getId())
//				.withId(actualResult.getId()).withCreatedAt(actualResult.getCreatedAt())
//				.withUpdatedAt(actualResult.getUpdatedAt());
//		assertThat(actualResult).isNotNull();
//		assertThat(actualResult).isEqualTo(expectedResult);
//	}

}
